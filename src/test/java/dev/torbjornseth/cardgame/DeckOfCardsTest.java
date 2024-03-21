package dev.torbjornseth.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckOfCardsTest {
  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }

  @AfterEach
  void tearDown() {
    deck = null;
  }

  @Test
  void dealHandPositive() {
    PlayingCard[] hand = deck.dealHand(5);
    assertEquals(5, hand.length);
    assertNotNull(hand[0]);
  }

  @Test
  void dealHandLengthTest() {
    PlayingCard[] hand = deck.dealHand(5);

    for (PlayingCard card : hand) {
      assertNotNull(card);
    }
  }

  @Test
  void shuffleTestFirstCard() {
    // Deal two hands from the same deck
    // The hands are shuffled between every hand
    PlayingCard[] deck1 = deck.dealHand(52);
    PlayingCard[] deck2 = deck.dealHand(52);

    assertNotEquals(deck1[0], deck2[0]);
  }
}