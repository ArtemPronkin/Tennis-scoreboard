package com.project.pet_project4;

import app.serviceMatch.matchesScoreCalculations.ExceptionScore;
import app.serviceMatch.matchesScoreCalculations.MatchScore;
import app.serviceMatch.matchesScoreCalculations.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchScoreTest {

    static void gameWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 4; i++) {
            matchScore.pointWon(player);
        }
    }
    static void setWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 6; i++) {
            gameWon(matchScore,player);
        }
    }
    static void tieBreakWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 7; i++) {
            matchScore.pointWon(player);
        }
    }

    @Test
    void SetShouldTie_BreakTest() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore, 0);
            gameWon(matchScore, 1);
        }
        Assertions.assertEquals(matchScore.getSetList().get(0).getState(),State.TIE_BREAK);
        tieBreakWon(matchScore,0);
        Assertions.assertEquals(matchScore.getSetList().get(0).getState(),State.PLAYER_ONE_WON);
    }
    @Test
    void StateListShouldTest1() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }

        tieBreakWon(matchScore,0);

        Assertions.assertEquals(matchScore.getSetList().get(0).getState(),State.PLAYER_ONE_WON);
    }
    @Test
    void StateListShouldTest2() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 7; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }
        tieBreakWon(matchScore,0);
        Assertions.assertEquals(matchScore.getSetList().get(0).getState(),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getSetList().get(1).getState(),State.ONGOING);
    }
    @Test
    void StateListShouldTest3() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();
        setWon(matchScore,0);
        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertEquals(matchScore.getSetList().get(0).getState(),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getSetList().get(1).getState(),State.PLAYER_TWO_WON);
        Assertions.assertEquals(matchScore.getSetList().get(2).getState(),State.PLAYER_TWO_WON);

    }
    @Test
    void matchShouldThrowTest1() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertThrows(ExceptionScore.class ,() -> matchScore.pointWon(0) );
    }
    @Test
    void matchShouldThrowTest2() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore,0);
        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertThrows(ExceptionScore.class ,() -> matchScore.pointWon(0) );
    }


}

