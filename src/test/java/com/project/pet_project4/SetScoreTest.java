package com.project.pet_project4;

import app.serviceMatch.matchesScoreCalculations.ScoreException;
import app.serviceMatch.matchesScoreCalculations.SetScore;
import app.serviceMatch.matchesScoreCalculations.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetScoreTest {
    SetScore setScore ;
    @BeforeEach
    void beforeTest(){
        setScore = new SetScore();
    }
    @Test
    void Must_ThrowException_WhenTryingToContinueCompletedSetWithSixWonPoint() throws ScoreException {
        for (int i = 0; i < 7; i++) {
            setScore.pointWon(0);
        }
        Assertions.assertThrows(ScoreException.class,() -> setScore.pointWon(0));
    }
    @Test
    void Must_SetScoreTieBreak_WhenSetScoreSixPointAgainstSixPoint() throws ScoreException {
        for (int i = 0; i < 6; i++) {
            setScore.pointWon(0);
            setScore.pointWon(1);
        }
        Assertions.assertEquals(setScore.getState(), State.TIE_BREAK);
    }
}
