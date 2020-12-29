package com.comfortly.questionnairecatalog.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AnswerPersistenceProducer {

    @PersistenceUnit(unitName = "answer-catalog-jpa")
    private EntityManagerFactory emAnswer;

    @Produces
    @Named(value = "answerEntityManager")
    @ApplicationScoped
    public EntityManager getAnswerEntityManager() {
        return emAnswer.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
