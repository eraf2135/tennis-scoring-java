package tennis;

public class Player {
    private String name;
    private int points;
    private int games;

    public Player(String name, int points, int games) {
        this.name = name;
        this.points = points;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incPoints() {
        this.points++;
    }

    public int getGames() {
        return games;
    }

    public void incGames() {
        this.games++;
    }
}
