package com.comfortly.questionnairecatalog.services.beans;

import com.comfortly.questionnairecatalog.lib.QuestionnaireData;
import com.comfortly.questionnairecatalog.models.converters.QuestionnaireDataConverter;
import com.comfortly.questionnairecatalog.models.entities.QuestionnaireDataEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class QuestionnaireDataBean {

    private Logger log = Logger.getLogger(QuestionnaireDataBean.class.getName());

    @Inject
    @Named(value = "questionnaireEntityManager")
    private EntityManager emQuestionnaire;

    public List<QuestionnaireData> getQuestionnaireData() {

        TypedQuery<QuestionnaireDataEntity> query = emQuestionnaire.createQuery("SELECT t FROM QuestionnaireDataEntity t WHERE t.active = TRUE", QuestionnaireDataEntity.class);

        return query.getResultList().stream()
                .map(QuestionnaireDataConverter::toDto).collect(Collectors.toList());
    }

    public QuestionnaireData createQuestion(QuestionnaireData questionnaireData) {

        QuestionnaireDataEntity questionnaireDataEntity = QuestionnaireDataConverter.toEntity(questionnaireData);

        try {
            beginTx();
            emQuestionnaire.persist(questionnaireDataEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        if (questionnaireDataEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return QuestionnaireDataConverter.toDto(questionnaireDataEntity);
    }

    public QuestionnaireData putQuestionData(Integer id, QuestionnaireData questionnaireData) {

        QuestionnaireDataEntity c = emQuestionnaire.find(QuestionnaireDataEntity.class, id);

        if (c == null) {
            return null;
        }

        QuestionnaireDataEntity updatedQuestionnaireDataEntity = QuestionnaireDataConverter.toEntity(questionnaireData);

        try {
            beginTx();
            updatedQuestionnaireDataEntity.setId(c.getId());
            updatedQuestionnaireDataEntity = emQuestionnaire.merge(updatedQuestionnaireDataEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return QuestionnaireDataConverter.toDto(updatedQuestionnaireDataEntity);
    }

    public boolean deleteQuestionData(Integer id) {

        QuestionnaireDataEntity questionnaireData = emQuestionnaire.find(QuestionnaireDataEntity.class, id);

        if (questionnaireData != null) {
            try {
                beginTx();
                emQuestionnaire.remove(questionnaireData);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!emQuestionnaire.getTransaction().isActive()) {
            emQuestionnaire.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emQuestionnaire.getTransaction().isActive()) {
            emQuestionnaire.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emQuestionnaire.getTransaction().isActive()) {
            emQuestionnaire.getTransaction().rollback();
        }
    }
}
