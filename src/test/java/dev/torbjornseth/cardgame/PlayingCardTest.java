package dev.torbjornseth.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayingCardTest {
  private PlayingCard card;

  @BeforeEach
  void setUp() {
    card = new PlayingCard('S', 1);
  }

  @AfterEach
  void tearDown() {
    card = null;
  }

  @Test
  void suit() {
    assertEquals('S', card.suit());
  }

  @Test
  void face() {
    assertEquals(1, card.face());
  }

  @Test
  void testEquals() {
    PlayingCard card2 = new PlayingCard('S', 1);
    assertEquals(card, card2);
  }

  @Test
  void testHashCode() {
    PlayingCard card2 = new PlayingCard('S', 1);
    assertEquals(card.hashCode(), card2.hashCode());
  }
}