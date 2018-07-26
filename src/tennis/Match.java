package tennis;

import java.util.*;

import static tennis.Utils.*;

public class Match {
    //I could make a player object, game object etc but seems overkill
    private Map<String, Integer> gamePointScore = new HashMap<>();
    private Map<String, Integer> setScore = new HashMap<>(); //set as in tennis set, not the operation set :)
    private ArrayList<String> players = new ArrayList<>();

    public Match(String playerOne, String playerTwo) {
        players.add(playerOne);
        players.add(playerTwo);
        startGame();
        startSet();
    }

    private void startSet() {
        setScore.put(players.get(0), 0);
        setScore.put(players.get(1), 0);
    }

    private void startGame() {
        gamePointScore.clear();
        gamePointScore.put(players.get(0), 0);
        gamePointScore.put(players.get(1), 0);
    }

    public void pointWonBy(String player) {
        if (isSetFinished(setScore)) {
            throw new UnsupportedOperationException("You've already finished the match! It was " + score());
        }

        int newScore = gamePointScore.get(player) + 1;
        gamePointScore.put(player, newScore);
        int aheadBy = newScore - gamePointScore.get(getOpponent(players, player));

        if (isTiebreaker(setScore)) {
            if (newScore >= 7 && aheadBy >= 2) {
                gameWonBy(player);
            }
            return;
        }

        if (newScore >= 4 && aheadBy >= 2) {
            gameWonBy(player);
        }
    }

    private void gameWonBy(String player) {
        setScore.put(player, setScore.get(player) + 1);
        startGame();
    }

    public String score() {
        return getSetScore() + ", " + getGameScore();
    }

    private String getGameScore() {
        String p1 = players.get(0);
        String p2 = players.get(1);
        int p1Score = gamePointScore.get(p1);
        int p2Score = gamePointScore.get(p2);

        if (isTiebreaker(setScore)) {
            return p1Score + "-" + p2Score;
        }

        if (p1Score == 3 && p1Score == p2Score) {
            return "Deuce";
        }

        if (p1Score >= 3 && p2Score >= 3) {
            if (p1Score > p2Score) {
                return "Advantage " + p1;
            } else {
                return "Advantage " + p2;
            }
        }

        return tennisify(p1Score)
                + "-"
                + tennisify(p2Score);
    }

    private String getSetScore() {
        return setScore.get(players.get(0))
                + "-"
                + setScore.get(players.get(1));
    }
}
