package com.ludigi.flashcards;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

    public QuestionRepository() {
        questions.add(new Question(1, "2+2", "4"));
    }

    public List<Question> findAll() {
        return questions;
    }

    Optional<Question> findById(Integer id) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        if (id < 0 || id > questions.size()) {
            return Optional.empty();
        }
        return Optional.of(questions.get(id - 1));
    }
    
    public Integer add(Question question) {
        if (question.getId() != null) {
            throw new IllegalArgumentException("Question already has id");
        }
        question.setId(questions.size() + 1);
        questions.add(question);
        return question.getId();
    }

}