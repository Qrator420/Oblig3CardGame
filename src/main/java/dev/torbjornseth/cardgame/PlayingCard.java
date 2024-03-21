package dev.torbjornseth.cardgame;

/**
 * Represents a playing card. A playing card has a number (face) between 1 and 13, where 1 is called
 * an Ace, 11 = Knight, 12 = Queen and 13 = King. The card can also be one of 4 suits: Spade, Heart,
 * Diamonds and Clubs.
 *
 * @param suit 'S'=spade, 'H'=heart, 'D'=diamonds, 'C'=clubs
 * @param face a number between 1 and 13
 * @author ntnu
 * @version 2021-03-13
 */
public record PlayingCard(char suit, int face) {

  /**
   * Creates an instance of a PlayingCard with a given suit and face. The face value is an integer
   * between 1 and 13, where 11 represents the jack, 12 represents the queen and 13 represents the
   * king. The Ace is represented by the number 1.
   *
   * <p>If the suit or face are invalid, an {@code IllegalArgumentException} is thrown.</p>
   *
   * @param suit The suit of the card, as a single character. 'S' for Spades, 'H' for Heart, 'D' for
   *             Diamonds and 'C' for clubs
   * @param face The face value of the card, an integer between 1 and 13
   * @throws IllegalArgumentException if suit or face have invalid values.
   */
  public PlayingCard {
    if (suit != 'H' && suit != 'D' && suit != 'C' && suit != 'S') {
      throw new IllegalArgumentException("Parameter suit must be one of H, D, C or S");
    }

    if (face < 1 || face > 13) {
      throw new IllegalArgumentException("Parameter face must be a number between 1 to 13");
    }

  }

  /**
   * Returns the suit and face of the card as a string. A 4 of hearts is returned as the string
   * "4H".
   *
   * @return the face and suit of the card as a string
   */
  public String getAsString() {
    return String.format("%s%s", suit, face);
  }

  /**
   * Returns the suit of the card, 'S' for Spades, 'H' for Heart, 'D' for Diamonds and 'C' for
   * clubs.
   *
   * @return the suit of the card
   */
  @Override
  public char suit() {
    return suit;
  }

  /**
   * Returns the face of the card (value between 1 and 13).
   *
   * @return the face of the card
   */
  @Override
  public int face() {
    return face;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayingCard otherCard = (PlayingCard) o;
    return suit() == otherCard.suit() && face() == otherCard.face();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 31 * hash + suit();
    hash = 31 * hash + face();
    return hash;
  }
}
