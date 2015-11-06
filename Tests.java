import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mounica Arroju
 */
public class Tests {
    private String inputHand1, inputHand2;
    private Hand hand1, hand2;
    private PokerJudge judge1;

    @Test
    public void testRoyalFlush() {
        inputHand1 = "AHKHQHJHTH";
        hand1 = new Hand(inputHand1);
        assertEquals(10, hand1.getRank());
    }

    @Test
    public void testStraightFlush() {
        inputHand1 = "9C8C7C6C5C";
        hand1 = new Hand(inputHand1);
        assertEquals(9, hand1.getRank());
    }

    @Test
    public void testFour() {
        inputHand1 = "AHASADACKH";
        hand1 = new Hand(inputHand1);
        assertEquals(8, hand1.getRank());
    }

    @Test
    public void testFullHouse() {
        inputHand1 = "AHASADKHKS";
        hand1 = new Hand(inputHand1);
        assertEquals(7, hand1.getRank());
    }

    @Test
    public void testFlush() {
        inputHand1 = "ASTS7S6S2S";
        hand1 = new Hand(inputHand1);
        assertEquals(6, hand1.getRank());
    }

    @Test
    public void testStraight() {
        inputHand1 = "5C4D3S2HAH";
        hand1 = new Hand(inputHand1);
        assertEquals(5, hand1.getRank());
    }

    @Test
    public void testThree() {
        inputHand1 = "AHASADKSQC";
        hand1 = new Hand(inputHand1);
        assertEquals(4, hand1.getRank());
    }

    @Test
    public void testTwoPair() {
        inputHand1 = "ADASKCKDQS";
        hand1 = new Hand(inputHand1);
        assertEquals(3, hand1.getRank());
    }

    @Test
    public void testOnePair() {
        inputHand1 = "AHASKCJDQS";
        hand1 = new Hand(inputHand1);
        assertEquals(2, hand1.getRank());
    }

    @Test
    public void testHighCard() {
        inputHand1 = "ADKSQDJC2S";
        hand1 = new Hand(inputHand1);
        assertEquals(1, hand1.getRank());
    }

    @Test
    //should throw an exception
    public void testValidHand() {
        inputHand1 = "ADADQDJC2S";
        hand1 = new Hand(inputHand1);
    }

    @Test
    public void testHighCardHands() {
        inputHand1 = "ADKSQDJC2S";
        hand1 = new Hand(inputHand1);
        inputHand2 = "3DKSQDJC2S";
        hand2 = new Hand(inputHand2);
        judge1 = new PokerJudge();
        assertEquals("Hand one wins", judge1.judge(hand1, hand2));
    }

    @Test
    public void sampleTest1() {
        inputHand1 = "2HQS2D2S5H";
        hand1 = new Hand(inputHand1);
        inputHand2 = "5C3CASQH9H";
        hand2 = new Hand(inputHand2);
        judge1 = new PokerJudge();
        assertEquals("Hand one wins", judge1.judge(hand1, hand2));
    }

    @Test
    public void sampleTest2() {
        inputHand1 = "3H5DACKH2D";
        hand1 = new Hand(inputHand1);
        inputHand2 = "2S3S4S5S6S";
        hand2 = new Hand(inputHand2);
        judge1 = new PokerJudge();
        assertEquals("Hand two wins", judge1.judge(hand1, hand2));
    }

    @Test
    public void testTie() {
        inputHand1 = "3H5DACKH2D";
        hand1 = new Hand(inputHand1);
        inputHand2 = "3H5DACKH2D";
        hand2 = new Hand(inputHand2);
        judge1 = new PokerJudge();
        assertEquals("Its a Tie!", judge1.judge(hand1, hand2));
    }

}
