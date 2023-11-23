package com.project.pet_project4;

import app.serviceMatch.matchesScoreCalculations.ScoreException;
import app.serviceMatch.matchesScoreCalculations.MatchScore;
import app.serviceMatch.matchesScoreCalculations.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchScoreTest {

    static void gameWon(MatchScore matchScore, int player) throws ScoreException {
        for (int i = 0; i < 4; i++) {
            matchScore.pointWon(player);
        }
    }

    static void setWon(MatchScore matchScore, int player) throws ScoreException {
        for (int i = 0; i < 6; i++) {
            gameWon(matchScore, player);
        }
    }

    static void tieBreakWon(MatchScore matchScore, int player) throws ScoreException {
        for (int i = 0; i < 7; i++) {
            matchScore.pointWon(player);
        }
    }

    @Test
    void Must_SetStateTieBreak_WhenSetScoreSixPointAgainstSixPoint() throws ScoreException {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore, 0);
            gameWon(matchScore, 1);
        }
        Assertions.assertEquals(matchScore.getSetList().get(0).getState(), State.TIE_BREAK);
    }

    @Test
    void Must_SetStateOngoing_WhenSetScoreMoreSixPointButLeadIsLessOnePoint() throws ScoreException {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 7; i++) {
            gameWon(matchScore, 0);
            gameWon(matchScore, 1);
        }
        Assertions.assertEquals(matchScore.getSetList().get(1).getState(), State.ONGOING);
    }

    @Test
    void Must_SetStatePlayerWon_WhenSetScoreMoreSixPointButLeadIsMoreOnePoint() throws ScoreException {
        MatchScore matchScore = new MatchScore();
        setWon(matchScore, 0);
        setWon(matchScore, 1);
        setWon(matchScore, 1);

        Assertions.assertEquals(matchScore.getSetList().get(0).getState(), State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getSetList().get(1).getState(), State.PLAYER_TWO_WON);
        Assertions.assertEquals(matchScore.getSetList().get(2).getState(), State.PLAYER_TWO_WON);

    }

    @Test
    void Must_ThrowException_WhenTryingToContinueCompletedMatchWithTwoWonSet() throws ScoreException {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore, 1);
        setWon(matchScore, 1);

        Assertions.assertThrows(ScoreException.class, () -> matchScore.pointWon(0));
    }

    @Test
    void Must_ThrowException_WhenTryingToContinueCompletedMatchWithThreeWonSet() throws ScoreException {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore, 0);
        setWon(matchScore, 1);
        setWon(matchScore, 1);

        Assertions.assertThrows(ScoreException.class, () -> matchScore.pointWon(0));
    }


}

