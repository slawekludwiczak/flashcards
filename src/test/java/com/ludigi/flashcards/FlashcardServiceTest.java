package com.ludigi.flashcards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void shouldReturnTrueForCorrectAnswer() {
        UUID id = UUID.randomUUID();
        mockFindById(id);
        Optional<Boolean> result = flashcardService.verifyAnswer(id, "4");
        assertTrue(result.get());
    }

    @Test
    void shouldReturnFalseForInorrectAnswer() {
        UUID id = UUID.randomUUID();
        mockFindById(id);
        Optional<Boolean> result = flashcardService.verifyAnswer(id, "5");
        assertFalse(result.get());
    }

    private void mockFindById(UUID id) {
        when(flashcardRepository.findById(id))
                .thenReturn(
                        Optional.of(new Flashcard(id, new Question(1, "2+2", "4")))
                );
    }

    @Test
    void shouldReturnEmptyForNonExistingFlashcard() {
        when(flashcardRepository.findById(any())).thenReturn(Optional.empty());
        Optional<Boolean> result = flashcardService.verifyAnswer(UUID.randomUUID(), "5");
        assertTrue(result.isEmpty());
    }

}