package com.ludigi.flashcards;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FlashcardService {
    private final QuestionRepository questions;
    private final FlashcardRepository flashcards;

    public FlashcardService(QuestionRepository questions, FlashcardRepository flashcards) {
        this.questions = questions;
        this.flashcards = flashcards;
    }

    Optional<Flashcard> drawFlashcard() {
        UUID id = UUID.randomUUID();
        Optional<Flashcard> flashcard = questions
                .findRandom()
                .map(q -> new Flashcard(id, q));
        flashcard.ifPresent(flashcards::add);
        return flashcard;
    }

    Optional<Boolean> verifyAnswer(UUID id, String answer) {
        Optional<Flashcard> flashcard = flashcards.findById(id);
        flashcard.ifPresent(value -> value.setAnswer(answer));
        return flashcard.map(Flashcard::isAnswerCorrect);
    }
}