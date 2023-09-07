package com.project.pet_project4.Score;

import lombok.Data;

import static com.project.pet_project4.Score.State.*;

@Data
public class Match extends Score<State> {
    GameScore gameScore;
    SetScore curSetScore;
    SetScore setScore1;
    SetScore setScore2;
    SetScore setScore3;

    @Override
    protected State getZeroScore() {
        return ONGOING;
    }

    @Override
    State pointWon(int playerNumber) {
        var statusGame = gameScore.pointWon(playerNumber);

        if (statusGame.equals(PLAYER_ONE_WON)||statusGame.equals(PLAYER_TWO_WON)){
            curSetScore.pointWon(playerNumber);
            gameScore = new GameScore();
        }
        
        if (statusGame.equals(PLAYER_ONE_WON)||statusGame.equals(PLAYER_TWO_WON)){
            setScore1.pointWon(playerNumber);
            gameScore = new GameScore();
        }

    }
}
