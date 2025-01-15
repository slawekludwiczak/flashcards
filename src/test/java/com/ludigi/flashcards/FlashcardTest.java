package com.ludigi.flashcards;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    @Test
    void shouldReturnTrueForCorrectAnswer() {
        Flashcard flashcard = new Flashcard(UUID.randomUUID(), new Question(1, "2+2", "4"));
        flashcard.setAnswer("4");
        assertTrue(flashcard.isAnswerCorrect());
    }

    @Test
    void shouldReturnFalseForCorrectAnswer() {
        Flashcard flashcard = new Flashcard(UUID.randomUUID(), new Question(1, "2+2", "4"));
        flashcard.setAnswer("5");
        assertFalse(flashcard.isAnswerCorrect());
    }

    @Test
    void shouldNotThrowForNullAnswer() {
        Flashcard flashcard = new Flashcard(UUID.randomUUID(), new Question(1, "2+2", "4"));
        assertDoesNotThrow(flashcard::isAnswerCorrect);
    }
}