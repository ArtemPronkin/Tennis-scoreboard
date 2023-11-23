package com.project.pet_project4;

import app.serviceMatch.matchesScoreCalculations.ScoreException;
import app.serviceMatch.matchesScoreCalculations.State;
import app.serviceMatch.matchesScoreCalculations.TieBreakScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiebreakTest {

    TieBreakScore tiebreak;
    @BeforeEach
    void setTiebreakTest(){
        tiebreak = new TieBreakScore();
    }
    @Test
    void Must_TieBreakStatePlayerWon_WhenGameScoreSevenPointAgainstZeroPoint() throws ScoreException {
        for (int i = 0; i < 7; i++) {
            tiebreak.pointWon(0);
        }
        Assertions.assertEquals(tiebreak.getState(),State.PLAYER_ONE_WON);

    }
    @Test
    void Must_ThrowException_WhenTryingToContinueCompletedTieBreak() throws ScoreException {
        for (int i = 0; i < 7; i++) {
            tiebreak.pointWon(0);
        }
        Assertions.assertEquals(tiebreak.getState(),State.PLAYER_ONE_WON);
        Assertions.assertThrows(ScoreException.class,()-> tiebreak.pointWon(0));
    }
    @Test
    void Must_TieBreakStateOngoing_WhenTieBreakScoreLeadIsLessTwoPoint() throws ScoreException {
        for (int i = 0; i < 10; i++) {
            tiebreak.pointWon(0);
            tiebreak.pointWon(1);
        }
        Assertions.assertEquals(tiebreak.getState(), State.ONGOING);
    }
    @Test
    void Must_TieBreakStatePlayerWon_WhenGameScoreLeadIsMoreTwoPoint() throws ScoreException {
        for (int i = 0; i < 10; i++) {
            tiebreak.pointWon(0);
            tiebreak.pointWon(1);
        }
        tiebreak.pointWon(1);
        tiebreak.pointWon(1);
        Assertions.assertEquals(tiebreak.getState(), State.PLAYER_TWO_WON);
    }
}
