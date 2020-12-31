package com.comfortly.questionnairecatalog.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "questionnaire_data")
@NamedQueries(value =
        {
                @NamedQuery(name = "QuestionnaireDataEntity.getAll",
                        query = "SELECT question FROM QuestionnaireDataEntity question")
        })
public class QuestionnaireDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "type")
    private String type;

    @Column(name = "possible_answers")
    private String possibleAnswers;

    @Column(name = "active")
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(String possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}