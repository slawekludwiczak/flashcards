package com.ludigi.flashcards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlashcardControllerTest {
    @MockitoBean
    private FlashcardService flashcardService;
    @Autowired
    private WebTestClient testClient;

    @Test
    void shouldReturn404WhenNoQuestions() {
        when(flashcardService.drawFlashcard()).thenReturn(Optional.empty());
        testClient.post().uri("/api/flashcards/random")
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody().isEmpty();
    }

    @Test
    void shouldReturn200WithBodyWhenFlashcardIsDrawn() {
        Flashcard flashcard = new Flashcard(
                UUID.fromString("c8b24535-b5ec-4e70-b7a8-cce727778d0a"),
                new Question(1, "2+2", "4")
        );
        when(flashcardService.drawFlashcard())
                .thenReturn(Optional.of(flashcard));
        testClient.post().uri("/api/flashcards/random")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("""
                        {
                            "id": "%s",
                            "question": "%s"
                        }
                        """.formatted(flashcard.getId(), flashcard.getQuestion().getQuestion()));
    }
}