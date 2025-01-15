package com.ludigi.flashcards;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FlashcardController {
    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("/api/flashcards/random")
    ResponseEntity<?> getRandomFlashcard() {
        return flashcardService.drawFlashcard()
                .map(fc -> ResponseEntity.ok(new FlashcardController.DrawnQuestionResponse(fc)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/flashcards/{id}/answer")
    ResponseEntity<Boolean> verifyAnswer(@PathVariable("id") UUID id,
                                         @RequestBody String answer) {
        return flashcardService.verifyAnswer(id, answer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private record DrawnQuestionResponse(UUID id, String question) {
        public DrawnQuestionResponse(Flashcard flashCard) {
            this(flashCard.getId(), flashCard.getQuestion().getQuestion());
        }
    }
}
