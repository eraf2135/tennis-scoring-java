package tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchTest {
    @Test
    public void scoreConversionToFifteenShouldWork() {
        Match match = new Match("player 1", "player 2");
        match.pointWonBy("player 1");
        assertEquals("0-0, 15-0", match.score());
        match.pointWonBy("player 2");
        assertEquals("0-0, 15-15", match.score());
    }

    @Test
    public void scoreConversionTo30ShouldWork() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 2; i++) {
            match.pointWonBy("player 1");
        }
        assertEquals("0-0, 30-0", match.score());
        for (int i = 0; i < 2; i++) {
            match.pointWonBy("player 2");
        }
        assertEquals("0-0, 30-30", match.score());
    }

    @Test
    public void scoreConversionTo40ShouldWork() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 3; i++) {
            match.pointWonBy("player 1");
        }
        assertEquals("0-0, 40-0", match.score());
        Match match2 = new Match("player 1", "player 2");
        for (int i = 0; i < 3; i++) {
            match2.pointWonBy("player 2");
        }
        assertEquals("0-0, 0-40", match2.score());
    }

    @Test
    public void scoreConversionToDeuceShouldWork() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 3; i++) {
            match.pointWonBy("player 1");
            match.pointWonBy("player 2");
        }
        assertEquals("0-0, Deuce", match.score());
    }

    @Test
    public void scoreConversionToAdvantagesShouldWork() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 3; i++) {
            match.pointWonBy("player 1");
            match.pointWonBy("player 2");
        }
        match.pointWonBy("player 1");
        assertEquals("0-0, Advantage player 1", match.score());
        match.pointWonBy("player 2");
        match.pointWonBy("player 2");
        assertEquals("0-0, Advantage player 2", match.score());
    }

    @Test
    public void gameScoreUpdates() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 4; i++) {
            match.pointWonBy("player 1");
        }
        assertEquals("1-0, 0-0", match.score());
    }

    @Test
    public void gameScoreUpdatesForAdvantageGame() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 3; i++) {
            match.pointWonBy("player 1");
            match.pointWonBy("player 2");
        }
        match.pointWonBy("player 1");
        match.pointWonBy("player 1");
        assertEquals("1-0, 0-0", match.score());
    }

    @Test
    public void matchFinishedWhenReach6AndWinBy2() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 16; i++) {
            match.pointWonBy("player 1");
        }
        for (int i = 0; i < 24; i++) {
            match.pointWonBy("player 2");
        }
        assertEquals("4-6, 0-0", match.score());
        assertThrows(UnsupportedOperationException.class, ()->match.pointWonBy("player 2"));
    }

    @Test
    public void tiebreakStartsAt6All() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 20; i++) {
            match.pointWonBy("player 1");
        }
        for (int i = 0; i < 24; i++) {
            match.pointWonBy("player 2");
        }
        for (int i = 0; i < 5; i++) {
            match.pointWonBy("player 1");
        }
        assertEquals("6-6, 1-0", match.score());
    }

    @Test
    public void tiebreakFinishesAt7AndWinBy2() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 20; i++) {
            match.pointWonBy("player 1");
        }
        for (int i = 0; i < 24; i++) {
            match.pointWonBy("player 2");
        }
        for (int i = 0; i < 4; i++) {
            match.pointWonBy("player 1");
        }
        //tiebreak
        for (int i = 0; i < 5; i++) {
            match.pointWonBy("player 1");
        }
        for (int i = 0; i < 7; i++) {
            match.pointWonBy("player 2");
        }
        assertEquals("6-7, 0-0", match.score());
    }

    @Test
    public void tiebreakContinuesAt6AllWinBy2() {
        Match match = new Match("player 1", "player 2");
        for (int i = 0; i < 20; i++) {
            match.pointWonBy("player 1");
        }
        for (int i = 0; i < 24; i++) {
            match.pointWonBy("player 2");
        }
        for (int i = 0; i < 4; i++) {
            match.pointWonBy("player 1");
        }
        //tiebreak
        for (int i = 0; i < 6; i++) {
            match.pointWonBy("player 1");
            match.pointWonBy("player 2");
        }
        match.pointWonBy("player 2");
        assertEquals("6-6, 6-7", match.score());
        match.pointWonBy("player 2");
        assertEquals("6-7, 0-0", match.score());
    }
}
