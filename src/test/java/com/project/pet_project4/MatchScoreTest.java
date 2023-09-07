package com.project.pet_project4;

import com.project.pet_project4.Score.ExceptionScore;
import com.project.pet_project4.Score.MatchScore;
import com.project.pet_project4.Score.SetScore;
import com.project.pet_project4.Score.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    static void gameWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 4; i++) {
            matchScore.pointWon(player);
        }
    }
    static void setWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 7; i++) {
            gameWon(matchScore,player);
        }
    }
    static void matchWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 2; i++) {
            setWon(matchScore,player);
        }
    }
    static void tieBreakWon(MatchScore matchScore,int player) throws ExceptionScore {
        for (int i = 0; i < 7; i++) {
            matchScore.pointWon(player);
        }
    }


    @Test
    void test() throws ExceptionScore {
        SetScore setScore = new SetScore();
        for (int i = 0; i < 7; i++) {
                setScore.pointWon(0);
            }
        Assertions.assertThrows(ExceptionScore.class,() -> setScore.pointWon(0));
    }

    @Test
    void test1() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();
        matchWon(matchScore,0);
        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test2() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();
        setWon(matchScore,0);
        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.WAIT);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test3() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }

        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.TIE_BREAK);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.WAIT);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test4() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }
        for (int i = 0; i < 6; i++) {
            matchScore.pointWon(0);
        }

        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.TIE_BREAK);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.WAIT);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test5() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 6; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }

        tieBreakWon(matchScore,0);

        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.WAIT);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test6() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        for (int i = 0; i < 7; i++) {
            gameWon(matchScore,0);
            gameWon(matchScore,1);
        }
        tieBreakWon(matchScore,0);
        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.ONGOING);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.WAIT);
    }
    @Test
    void test7() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();
        setWon(matchScore,0);
        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertEquals(matchScore.getStateSetList().get(0),State.PLAYER_ONE_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(1),State.PLAYER_TWO_WON);
        Assertions.assertEquals(matchScore.getStateSetList().get(2),State.PLAYER_TWO_WON);

    }
    @Test
    void test8() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertThrows(ExceptionScore.class ,() -> matchScore.pointWon(0) );
    }
    @Test
    void matchS1() throws ExceptionScore {
        MatchScore matchScore = new MatchScore();

        setWon(matchScore,0);
        setWon(matchScore,1);
        setWon(matchScore,1);

        Assertions.assertThrows(ExceptionScore.class ,() -> matchScore.pointWon(0) );
    }


}

