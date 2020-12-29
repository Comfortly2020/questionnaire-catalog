package com.comfortly.questionnairecatalog.lib;

public enum QuestionType {
    TEXT("TEXT"),
    NUMBER("NUMBER"),
    NUMBER_DECIMAL("NUMBER_DECIMAL"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    SINGLE_CHOICE("SINGLE_CHOICE"),
    UNKNOWN("UNKNOWN");

    private final String text;

    QuestionType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

