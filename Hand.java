import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Mounica Arroju
 *         <p/>
 *         class to represent a Poker Hand
 */

public class Hand implements Comparable<Hand> {
    private final int SIZE_OF_HAND = 5;
    private Card[] handSet;
    private int rank;
    private int handSum;

    public Hand(String inputHand) {
        this.handSet = new Card[SIZE_OF_HAND];

        //split the input in pairs of two characters
        String[] input = inputHand.split("(?<=\\G..)");

        try {
            for (int i = 0; i < handSet.length; i++) {
                handSet[i] = new Card(input[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            checkHandValidity();
            computeHandSum();
            computeRank();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getRank() {
        return rank;
    }

    /**
     * Check if its a valid hand assuming only one deck of cards is played
     *
     * @throws Exception if two cards in a hand have identical suits and values
     */
    private void checkHandValidity() throws Exception {
        for (int i = 0; i < SIZE_OF_HAND; i++) {
            for (int j = i + 1; j < SIZE_OF_HAND; j++) {
                if (handSet[i].getCardRank() == handSet[j].getCardRank() && handSet[i].getSuit() == handSet[j].getSuit()) {
                    throw new Exception("Not a valid Hand!");
                }
            }
        }
    }

    /**
     * Computes the rank of the hand
     */
    void computeRank() {

        if (isStraight()) {
            if (isFlush()) {
                if (isRoyalFlush()) {
                    rank = 10;  //Royal Flush
                } else {
                    rank = 9;   //Straight Flush
                }
            } else {
                rank = 5;   //Straight
            }
        } else if (isFlush()) {
            rank = 6;   //Flush
        } else if (hasIdenticalCards()) {
            assignIdenticalRanks();
        } else {
            rank = 1;
        }
    }

    /**
     * This method is called when there are some cards with same number
     */
    private void assignIdenticalRanks() {
        HashMap<Integer, Integer> cardCounts = new HashMap<Integer, Integer>();
        int NoOfPairs = 0;
        int NoOfThrees = 0;

        for (int i = 0; i < SIZE_OF_HAND; i++) {
            if (!cardCounts.containsKey(handSet[i].getCardRank())) {
                cardCounts.put(handSet[i].getCardRank(), 1);
            } else {
                Integer key = handSet[i].getCardRank();
                cardCounts.put(key, cardCounts.get(key) + 1);
            }
        }

        for (Integer key : cardCounts.keySet()) {
            if (cardCounts.get(key) == 2) {
                NoOfPairs++;
            }
            if (cardCounts.get(key) == 3) {
                NoOfThrees++;
            }
            if (cardCounts.get(key) == 4) {
                rank = 8;   //Four of a Kind
            }
        }

        if (NoOfPairs == 1) {
            if (NoOfThrees == 1) {    //full house
                rank = 7;
            } else {                //Two of a Kind
                rank = 2;
            }
        } else if (NoOfThrees == 1) {    //Three of a Kind
            rank = 4;
        } else if (NoOfPairs == 2) {      //Two Pair
            rank = 3;
        }
    }

    /**
     * computes sum of values of all cards in the hand
     * This method is to break ties between hands and to
     * decide the Hand with the high card
     */
    void computeHandSum() {
        int value = 0;
        for (Card aHandSet : handSet) {
            value += aHandSet.getCardRank();
        }
        handSum = value;
    }

    @Override
    public int compareTo(Hand that) {
        if (this.rank > that.rank) {
            return 1;
        } else if (this.rank < that.rank) {
            return -1;
        } else {
            if (this.handSum > that.handSum) {
                return 1;
            } else if (this.handSum < that.handSum) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private boolean isRoyalFlush() {
        return handSum == 60;
    }

    /**
     * @return true if there are at least two cards with same value
     */
    private boolean hasIdenticalCards() {

        for (int i = 1; i < SIZE_OF_HAND; i++) {
            if (handSet[i].getCardRank() == handSet[i - 1].getCardRank()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFlush() {
        char tempSuit = handSet[0].getSuit();
        for (int i = 1; i < SIZE_OF_HAND; i++) {
            if (handSet[i].getSuit() != tempSuit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        Arrays.sort(handSet);

        int value = handSet[0].getCardRank();

        //Case when A has to be counted as 1
        if (value == 2 && handSet[SIZE_OF_HAND - 1].getCardRank() == 14) {
            handSet[SIZE_OF_HAND - 1].setCardRank(1);
            Arrays.sort(handSet);
            value = handSet[0].getCardRank();
        }

        for (int i = 1; i < SIZE_OF_HAND; i++) {
            if (handSet[i].getCardRank() - value != 1) {
                return false;
            }
            value = handSet[i].getCardRank();
        }

        return true;
    }


}
