package com.ludigi.flashcards;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class FlashcardRepository {
    private final Map<UUID, Flashcard> drawnQuestions = new HashMap<>();

    public void add(Flashcard flashCard) {
        this.drawnQuestions.put(flashCard.getId(), flashCard);
    }
}