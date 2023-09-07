package com.project.pet_project4;

import Score.ExceptionScore;
import Score.State;
import Score.TieBreak;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiebreakTest {

    TieBreak tiebreak;
    @BeforeEach
    void setTiebreakTest(){
        tiebreak = new TieBreak();
    }
    @Test
    void TieBreakShoulThrow() throws ExceptionScore {
        for (int i = 0; i < 7; i++) {
            tiebreak.pointWon(0);
        }
        Assertions.assertEquals(tiebreak.getState(),State.PLAYER_ONE_WON);
        Assertions.assertThrows(ExceptionScore.class,()-> tiebreak.pointWon(0));

    }
    @Test
    void TieBreakShouldNonStop() throws ExceptionScore {
        for (int i = 0; i < 10; i++) {
            tiebreak.pointWon(0);
            tiebreak.pointWon(1);
        }
        Assertions.assertEquals(tiebreak.getState(), State.ONGOING);
        tiebreak.pointWon(1);
        tiebreak.pointWon(1);
        Assertions.assertEquals(tiebreak.getState(),State.PLAYER_TWO_WON);
    }
}
