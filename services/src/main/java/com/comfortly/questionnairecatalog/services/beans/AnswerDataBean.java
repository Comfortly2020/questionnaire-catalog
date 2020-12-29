package com.comfortly.questionnairecatalog.services.beans;

import com.comfortly.questionnairecatalog.lib.AnswerData;
import com.comfortly.questionnairecatalog.models.converters.AnswerDataConverter;
import com.comfortly.questionnairecatalog.models.entities.AnswerDataEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class AnswerDataBean {

    private Logger log = Logger.getLogger(AnswerDataBean.class.getName());

    @Inject
    @Named(value = "answerEntityManager")
    private EntityManager emAnswers;

    public List<AnswerData> getAnswersData(String userId, Integer tripId) {

        TypedQuery<AnswerDataEntity> query = emAnswers.createQuery("SELECT t FROM AnswerDataEntity t WHERE t.userId = :user AND t.tripId = :trip", AnswerDataEntity.class);
        query.setParameter("user", userId);
        query.setParameter("trip", tripId);

        return query.getResultList().stream()
                .map(AnswerDataConverter::toDto).collect(Collectors.toList());
    }

    public void postAnswers(List<AnswerData> answerData) {

        List<AnswerDataEntity> anserDataEntityList = answerData.stream()
                .map(AnswerDataConverter::toEntity).collect(Collectors.toList());

        try {
            beginTx();
            for (AnswerDataEntity answerDataEntity : anserDataEntityList) {
                emAnswers.persist(answerDataEntity);

            }
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        for (AnswerDataEntity answerDataEntity : anserDataEntityList) {
            if (answerDataEntity.getId() == null) {
                throw new RuntimeException("Entity was not persisted");
            }
        }
    }

    private void beginTx() {
        if (!emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().rollback();
        }
    }
}
