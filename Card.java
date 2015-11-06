/**
 * @author Mounica Arroju
 *         <p/>
 *         class to represent a Card
 */
public class Card implements Comparable<Card> {
    private final char suit;
    private final char number;
    private int cardRank;

    private static final char[] suits = {'C', 'S', 'H', 'D'};
    private static final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    public Card(String cardInput) {
        this.number = cardInput.charAt(0);
        this.suit = cardInput.charAt(1);

        checkValidSuit();
        checkValidNumberAndAssignCardRank();
    }

    private void checkValidSuit() {
        try {
            for (char aSuit : suits) {
                if (suit == aSuit) {
                    return;
                }
            }
            throw new Exception("Not a Valid Suit!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkValidNumberAndAssignCardRank() {
        try {
            for (int i = 0; i < numbers.length; i++) {
                if (number == numbers[i]) {
                    cardRank = i + 1;
                    return;
                }
            }

            throw new Exception("Not a Valid Card Number!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCardRank(int val) {
        cardRank = val;
    }

    public int getCardRank() {
        return cardRank;
    }

    public char getSuit() {
        return suit;
    }

    /**
     * compares two cards based on the value
     *
     * @param that Card object to be compared to
     * @return 1 if greater, -1 if lesser and 0 if equal
     */
    @Override
    public int compareTo(Card that) {
        if (this.cardRank > that.cardRank) {
            return 1;
        } else if (this.cardRank < that.cardRank) {
            return -1;
        } else {
            return 0;
        }
    }
}
