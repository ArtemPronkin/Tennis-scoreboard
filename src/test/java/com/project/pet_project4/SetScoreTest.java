package com.project.pet_project4;

import Controller.ServiceMatch.Score.ExceptionScore;
import Controller.ServiceMatch.Score.SetScore;
import Controller.ServiceMatch.Score.State;
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
    void SetScoreShouldThrowTest() throws ExceptionScore {
        for (int i = 0; i < 7; i++) {
            setScore.pointWon(0);
        }
        Assertions.assertThrows(ExceptionScore.class,() -> setScore.pointWon(0));
    }
    @Test
    void stateShouldTieBreak() throws ExceptionScore {
        for (int i = 0; i < 6; i++) {
            setScore.pointWon(0);
            setScore.pointWon(1);
        }
        Assertions.assertEquals(setScore.getState(), State.TIE_BREAK);
    }
}
