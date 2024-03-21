package dev.torbjornseth.cardgame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {
  private Hand hand;

  @BeforeEach
  void setUp() {
    hand = new Hand(new PlayingCard[] {
      new PlayingCard('S', 1),
      new PlayingCard('S', 2),
      new PlayingCard('S', 3),
      new PlayingCard('S', 4),
      new PlayingCard('S', 5)
    });
  }

  @AfterEach
  void tearDown() {
    hand = null;
  }

  @Test
  void checkFlushPositive() {
    assertEquals("Yes", hand.checkFlush(5));
  }

  @Test
  void checkFlushNegative() {
    hand = new Hand(new PlayingCard[] {
      new PlayingCard('S', 1),
      new PlayingCard('S', 2),
      new PlayingCard('S', 3),
      new PlayingCard('S', 4),
      new PlayingCard('H', 5)
    });
    assertEquals("No", hand.checkFlush(5));
    assertEquals("Yes", hand.checkFlush(4));
  }

  @Test
  void getCards() {
    PlayingCard[] cards = hand.getCards();
    assertEquals(5, cards.length);
    assertEquals('S', cards[0].suit());
    assertEquals(1, cards[0].face());
  }

  @Test
  void containsCard() {
    assertEquals("Yes", hand.containsCard('S', 1));
    assertEquals("No", hand.containsCard('H', 1));
  }

  @Test
  void getSum() {
    assertEquals(15, hand.getSum());
  }

  @Test
  void getHearts() {
    assertEquals("1S 2S 3S 4S 5S", hand.getSuits('S'));
    assertEquals("No hearts", hand.getSuits('H'));
  }
}