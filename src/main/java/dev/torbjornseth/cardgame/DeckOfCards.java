package dev.torbjornseth.cardgame;

import java.util.Random;

/**
 * Represents a deck of playing cards. A deck of cards contains 52 cards.
 * Each card is represented by an instance of the class PlayingCard.
 *
 * @version 1.0
 * @author Torbjørn Seth
 * @since 21.03.2024
 */
public class DeckOfCards {
  private final char[] suits = {'S', 'H', 'D', 'C'};
  private final int[] faces = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private PlayingCard[] deck;
  private final Random random = new Random();

  /**
   * Creates a new deck of cards. The deck contains 52 cards, where each card is
   * represented by an instance of the class PlayingCard.
   * The cards are ordered by suit and face value.
   *
   * @since 1.0
   */
  public DeckOfCards() {
    initCards();
  }

  private void initCards() {
    deck = new PlayingCard[52];
    int index = 0;
    for (char suit : suits) {
      for (int face : faces) {
        deck[index] = new PlayingCard(suit, face);
        index++;
      }
    }
  }

  /**
   * Returns the deck of cards as an array of playing cards.
   * The cards are shuffled before being returned.
   * The cards added to the return deck are removed from the original deck.
   *
   * @param n the number of cards to deal
   * @return an array of playing cards
   */
  public PlayingCard[] dealHand(int n) {
    if (n < 0 || n > 52) {
      throw new IllegalArgumentException("Parameter n must be a number between 0 and 52");
    }
    shuffle();
    PlayingCard[] cards = new PlayingCard[n];

    int i = 0;
    int limit = n;
    int spacing = 0;
    while (i < limit) {
      if (deck[i] == null) {
        // Skip this card, already dealt
        limit++;
        spacing++;
      } else {
        // i & spacing incremented together
        // using i - spacing to find original index
        cards[i - spacing] = deck[i];
        deck[i] = null;
      }
      i++;
    }
    initCards();
    return cards;
  }

  /**
   * Shuffles the deck of cards.
   * Each card in the deck is shuffled at least once.
   *
   * @since 1.0
   */
  void shuffle() {
    for (int i = 0; i < deck.length; i++) {
      int randomIndex = random.nextInt(deck.length);
      PlayingCard temp = deck[i];
      deck[i] = deck[randomIndex];
      deck[randomIndex] = temp;
    }
  }

}
