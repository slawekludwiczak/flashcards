package com.ludigi.flashcards;

import java.util.Objects;

public final class Question {
    private Integer id;
    private String question;
    private String solution;

    public Question(String question, String solution) {
        Objects.requireNonNull(question);
        Objects.requireNonNull(solution);
        this.question = question;
        this.solution = solution;
    }

    public Question(Integer id, String question, String solution) {
        this(question, solution);
        Objects.requireNonNull(id);
        this.id = id;
    }

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Question) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.question, that.question) &&
                Objects.equals(this.solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, solution);
    }

    @Override
    public String toString() {
        return "Question[" +
                "id=" + id + ", " +
                "question=" + question + ", " +
                "solution=" + solution + ']';
    }

}