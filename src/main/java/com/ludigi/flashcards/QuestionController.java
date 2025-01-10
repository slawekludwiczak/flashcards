package com.ludigi.flashcards;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/api/questions")
    ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionRepository.findAll());
    }

    @GetMapping("/api/questions/{id}")
    ResponseEntity<Question> getQuestion(@PathVariable("id") Integer id) {
        return questionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
