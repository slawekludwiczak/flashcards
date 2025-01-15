package com.ludigi.flashcards;

import java.util.Objects;
import java.util.UUID;

public class Flashcard {
    private UUID id;
    private Question question;
    private String answer;

    public Flashcard(UUID id, Question question) {
        this.id = id;
        this.question = question;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswerCorrect() {
       return Objects.equals(answer, question.getSolution());
    }
}
