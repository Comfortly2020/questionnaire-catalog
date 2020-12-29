package com.comfortly.questionnairecatalog.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class QuestionnairePersistenceProducer {

    @PersistenceUnit(unitName = "questionnaire-catalog-jpa")
    private EntityManagerFactory emQuestionnaire;

    @Produces
    @Named(value = "questionnaireEntityManager")
    @ApplicationScoped
    public EntityManager getTripEntityManager() {
        return emQuestionnaire.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
