package com.ludigi.flashcards;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

    public QuestionRepository() {
        questions.add(new Question(1, "2+2", "4"));
    }

    public List<Question> findAll() {
        return questions;
    }
}
