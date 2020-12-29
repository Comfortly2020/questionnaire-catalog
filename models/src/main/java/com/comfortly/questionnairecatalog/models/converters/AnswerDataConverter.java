package com.comfortly.questionnairecatalog.models.converters;

import com.comfortly.questionnairecatalog.lib.AnswerData;
import com.comfortly.questionnairecatalog.lib.QuestionType;
import com.comfortly.questionnairecatalog.models.entities.AnswerDataEntity;

public class AnswerDataConverter {

    public static AnswerData toDto(AnswerDataEntity entity) {

        AnswerData dto = new AnswerData();
        dto.setTripId(entity.getTripId());
        dto.setUserId(entity.getUserId());
        dto.setQuestionId(entity.getQuestionId());
        dto.setQuestion(entity.getQuestion());
        QuestionType questionType = QuestionType.UNKNOWN;
        for (QuestionType type : QuestionType.values()) {
            if (type.toString().equals(entity.getType())) {
                questionType = type;
                break;
            }
        }
        dto.setType(questionType);
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    public static AnswerDataEntity toEntity(AnswerData dto) {

        AnswerDataEntity entity = new AnswerDataEntity();
        entity.setTripId(dto.getTripId());
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setQuestionId(dto.getQuestionId());
        entity.setQuestion(dto.getQuestion());
        entity.setType(dto.getType().toString());
        entity.setAnswer(dto.getAnswer());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

}
