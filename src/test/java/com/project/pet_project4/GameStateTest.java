package com.project.pet_project4;

import App.ServiceMatch.MatchesScoreCalculations.ExceptionScore;
import App.ServiceMatch.MatchesScoreCalculations.GameScore;
import App.ServiceMatch.MatchesScoreCalculations.State;
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
    void AdvantageShouldNonStopTest() throws ExceptionScore {
        for (int i = 0; i < 20; i++) {
            gameScore.pointWon(0);
            gameScore.pointWon(1);
        }


        Assertions.assertEquals(gameScore.getState(),State.ONGOING);
    }
    @Test
    void AdvantageShouldThrowTest() throws ExceptionScore {
        for (int i = 0; i < 4; i++) {
            gameScore.pointWon(0);
        }
        Assertions.assertThrows(ExceptionScore.class,()->gameScore.pointWon(0));
    }
    @Test
    void GameShouldWon() throws ExceptionScore {
        for (int i = 0; i < 4; i++) {
            gameScore.pointWon(0);
        }
        Assertions.assertEquals(gameScore.getState(),State.PLAYER_ONE_WON);
    }

}
