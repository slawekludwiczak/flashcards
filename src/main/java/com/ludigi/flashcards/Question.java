package com.ludigi.flashcards;

import java.util.Objects;

public record Question(Integer id, String question, String solution) {
    public Question {
        Objects.requireNonNull(id);
        Objects.requireNonNull(question);
        Objects.requireNonNull(solution);
    }
}