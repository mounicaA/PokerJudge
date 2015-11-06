/**
 * @author Mounica Arroju
 *         <p/>
 *         A Poker Judge that takes two Poker Hands as input arguments and
 *         declares the winning hand
 *         <p/>
 *         Example Compile: javac PokerJudge.java
 *         Example Run: java PokerJudge 2HQS2D2S5H 5C3CASQH9H
 */
public class PokerJudge {

    public String judge(Hand h1, Hand h2) {

        String message = "";

        if (h1.compareTo(h2) > 0) {
            message = "Hand one wins";
        } else if (h1.compareTo(h2) < 0) {
            message = "Hand two wins";
        } else {
            message = "Its a Tie!";
        }

        return message;
    }

    public static void main(String[] args) {
        Hand h1, h2;
        try {
            h1 = new Hand(args[0]);
            h2 = new Hand(args[1]);

            PokerJudge pokerRound = new PokerJudge();
            System.out.println(pokerRound.judge(h1, h2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
