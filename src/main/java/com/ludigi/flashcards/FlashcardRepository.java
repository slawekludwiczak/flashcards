package com.ludigi.flashcards;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FlashcardRepository {
    private final Map<UUID, Flashcard> questions = new HashMap<>();

    public void add(Flashcard flashCard) {
        this.questions.put(flashCard.getId(), flashCard);
    }

    public Optional<Flashcard> findById(UUID id) {
        return Optional.ofNullable(questions.get(id));
    }
}