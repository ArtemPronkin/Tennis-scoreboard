package com.project.pet_project4;

import app.serviceMatch.matchesScoreCalculations.ScoreException;
import app.serviceMatch.matchesScoreCalculations.GameScore;
import app.serviceMatch.matchesScoreCalculations.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    private GameScore gameScore;
    @BeforeEach
    void setGame(){
        gameScore = new GameScore();
    }
    @Test
    void Must_GameStateOngoing_WhenGameScoreLeadIsLessOnePoint() throws ScoreException {
        for (int i = 0; i < 20; i++) {
            gameScore.pointWon(0);
            gameScore.pointWon(1);
        }


        Assertions.assertEquals(gameScore.getState(),State.ONGOING);
    }
    @Test
    void Must_ThrowException_WhenTryingToContinueCompletedGameWithFivePoint() throws ScoreException {
        for (int i = 0; i < 4; i++) {
            gameScore.pointWon(0);
        }
        Assertions.assertThrows(ScoreException.class,()->gameScore.pointWon(0));
    }
    @Test
    void Must_GameStatePlayerWon_WhenGameScoreFourPointAgainstZeroPoint() throws ScoreException {
        for (int i = 0; i < 4; i++) {
            gameScore.pointWon(0);
        }
        Assertions.assertEquals(gameScore.getState(),State.PLAYER_ONE_WON);
    }

}
