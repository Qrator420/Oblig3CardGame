package dev.torbjornseth.cardgame;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a hand of playing cards. A hand of cards contains a number of cards.
 * Each card is represented by an instance of the class PlayingCard.
 *
 * @version 1.1
 * @author Torbjørn Seth
 * @since 21.03.2024
 */
public class Hand {
  private final PlayingCard[] cards;

  /**
   * Creates a new hand of cards with the given cards.
   * The hand can contain any number of cards below 52.
   *
   * @param cards the cards in the hand
   * @since 1.0
   */
  public Hand(PlayingCard[] cards) {
    this.cards = cards;
  }

  /**
   * Checks if the hand contains a flush, i.e. if all the cards have the same suit.
   * The size of the flush is defined by the parameter n.
   *
   * @param n the number of cards that must have the same suit
   * @return true if the hand contains a flush, false otherwise
   */
  public String checkFlush(int n) {
    return Stream.of(cards)
        .collect(
            Collectors.groupingBy(
                PlayingCard::suit, Collectors.counting()
            )
        )
        .values()  // Get the counts
        .stream()  // Create a new stream
        // Check if any of the counts are greater than or equal to n
        .anyMatch(count -> count >= n) ? "Yes" : "No";
  }

  /**
   * Returns the cards in the hand.
   *
   * @return the cards in the hand.
   * @since 1.0
   */
  public PlayingCard[] getCards() {
    return cards;
  }

  /**
   * Checks if the hand contains a card with the given suit and face. The suit is represented by a
   * character, where 'S' is spades, 'H' is hearts, 'D' is diamonds and 'C' is clubs. The face is an
   * integer between 1 and 13.
   *
   * @param suit The suit of the card, 'S' for Spades, 'H' for Heart, 'D' for Diamonds and 'C' for
   *             clubs
   * @param face The face of the card, an integer between 1 and 13
   * @return {@code Yes} if the hand contains the card, {@code No} otherwise
   * @since 1.1
   */
  public String containsCard(char suit, int face) {
    PlayingCard referenceCard = new PlayingCard(suit, face);
    boolean contains = false;

    int i = 0;
    while (i < cards.length) {
      if (cards[i].equals(referenceCard)) {
        contains = true;
        i = cards.length;  // Break the loop
      }
      i++;
    }

    return contains ? "Yes" : "No";
  }

  /**
   * Returns the sum of the face values of the cards in the hand.
   * The sum is calculated by adding the face values of all the cards in the hand.
   *
   * @return the sum of the face values of the cards in the hand
   * @since 1.1
   */
  public int getSum() {
    return Stream.of(cards).map(PlayingCard::face).reduce(0, Integer::sum);
  }

  /**
   * Returns the cards in the hand as a string.
   * The cards are separated by a space.
   * If no cards of the given suit are in the hand, the string "No hearts" is returned.
   *
   * @param suit the suit of the cards to return
   * @return the cards in the hand as a string
   * @since 1.1
   */
  public String getSuits(char suit) {
    String value = Stream.of(cards)
        .filter(card -> card.suit() == suit)
        .sorted(Comparator.comparing(PlayingCard::face))
        .map(PlayingCard::getAsString)
        // Reduce the stream to a single string with spaces between the cards
        .reduce("", (a, b) -> a + " " + b);
    // Remove first space if the string is not empty, thereof the substring(1)
    return value.isEmpty() ? "No hearts" : value.substring(1);
  }
}
