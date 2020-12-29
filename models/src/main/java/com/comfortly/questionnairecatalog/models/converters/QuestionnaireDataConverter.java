package com.comfortly.questionnairecatalog.models.converters;

import com.comfortly.questionnairecatalog.lib.QuestionType;
import com.comfortly.questionnairecatalog.lib.QuestionnaireData;
import com.comfortly.questionnairecatalog.models.entities.QuestionnaireDataEntity;

public class QuestionnaireDataConverter {

    public static QuestionnaireData toDto(QuestionnaireDataEntity entity) {

        QuestionnaireData dto = new QuestionnaireData();
        dto.setId(entity.getId());
        dto.setQuestion(entity.getQuestion());
        QuestionType questionType = QuestionType.UNKNOWN;
        for (QuestionType type : QuestionType.values()) {
            if (type.toString().equals(entity.getType())) {
                questionType = type;
                break;
            }
        }
        dto.setType(questionType);
        dto.setActive(entity.getActive());

        return dto;
    }

    public static QuestionnaireDataEntity toEntity(QuestionnaireData dto) {

        QuestionnaireDataEntity entity = new QuestionnaireDataEntity();
        entity.setQuestion(dto.getQuestion());
        entity.setType(dto.getType().toString());
        entity.setActive(dto.getActive());

        return entity;
    }

}
