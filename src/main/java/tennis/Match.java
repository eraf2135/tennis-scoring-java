package tennis;

import java.util.*;

import static tennis.Utils.*;

public class Match {
    private ArrayList<Player> players = new ArrayList<>();

    public Match(String playerOne, String playerTwo) {
        players.add(new Player(playerOne, 0, 0));
        players.add(new Player(playerTwo, 0, 0));
    }

    private void startGame() {
        players.get(0).setPoints(0);
        players.get(1).setPoints(0);
    }

    public void pointWonBy(String playerName) {
        if (isSetFinished(players.get(0).getGames(), players.get(1).getGames())) {
            throw new UnsupportedOperationException("You've already finished the match! It was " + score());
        }

        Player pointWinningPlayer = players.stream().filter(x -> x.getName() == playerName).findFirst().get();
        Player opponent = players.stream().filter(x -> x.getName() != playerName).findFirst().get();

        pointWinningPlayer.incPoints();
        int newScore = pointWinningPlayer.getPoints();
        int aheadBy = newScore - opponent.getPoints();

        if (isTiebreaker(pointWinningPlayer.getGames(), opponent.getGames())) {
            if (newScore >= 7 && aheadBy >= 2) {
                gameWonBy(pointWinningPlayer);
            }
            return;
        }

        if (newScore >= 4 && aheadBy >= 2) {
            gameWonBy(pointWinningPlayer);
        }
    }

    private void gameWonBy(Player player) {
        player.incGames();
        startGame();
    }

    public String score() {
        String score = getSetScore() + ", " + getGameScore();
        System.out.println(score);
        return score;
    }

    private String getGameScore() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        int p1Points = p1.getPoints();
        int p2Points = p2.getPoints();

        if (isTiebreaker(p1.getGames(), p2.getGames())) {
            return p1Points + "-" + p2Points;
        }

        if (p1Points == 3 && p2Points == 3) {
            return "Deuce";
        }

        if (p1Points >= 3 && p2Points >= 3) {
            if (p1Points > p2Points) {
                return "Advantage " + p1.getName();
            } else {
                return "Advantage " + p2.getName();
            }
        }

        return tennisify(p1Points)
                + "-"
                + tennisify(p2Points);
    }

    private String getSetScore() {
        return players.get(0).getGames()
                + "-"
                + players.get(1).getGames();
    }

    public static void main(String[] args) {
        Match match = new Match("player 1", "player 2");
        match.pointWonBy("player 1");
        match.pointWonBy("player 2");
        // this will return "0-0, 15-15"
        match.score();

        match.pointWonBy("player 1");
        match.pointWonBy("player 1");
        // this will return "0-0, 40-15"
        match.score();

        match.pointWonBy("player 2");
        match.pointWonBy("player 2");
        // this will return "0-0, Deuce"
        match.score();

        match.pointWonBy("player 1");
        // this will return "0-0, Advantage player 1"
        match.score();

        match.pointWonBy("player 1");
        // this will return "1-0"
        match.score();
    }
}
