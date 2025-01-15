package com.ludigi.flashcards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlashcardServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private FlashcardRepository flashcardRepository;
    @InjectMocks
    private FlashcardService flashcardService;

    @Test
    void shouldReturnEmptyOptionalForEmptyQuestions() {
        when(questionRepository.findRandom()).thenReturn(Optional.empty());
        Optional<Flashcard> drawnFlashcard = flashcardService.drawFlashcard();
        assertTrue(drawnFlashcard.isEmpty());
    }

    @Test
    void shouldDrawFlashcardForExistingQuestion() {
        when(questionRepository.findRandom()).thenReturn(Optional.of(new Question(1, "2+2", "4")));
        Optional<Flashcard> drawnFlashcard = flashcardService.drawFlashcard();
        assertTrue(drawnFlashcard.isPresent());
    }

}