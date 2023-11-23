package app.serviceMatch.matchesScoreCalculations;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

import static app.serviceMatch.matchesScoreCalculations.State.*;

@EqualsAndHashCode(callSuper = true)
@Data

public class MatchScore extends Score<Integer> {

    private GameScore gameScore = new GameScore();

    private Integer currentNumberSet = 0;

    private TieBreak tieBreak = new TieBreak();

    private List<SetScore> setList = Arrays.asList(new SetScore(), new SetScore(), new SetScore());


    public MatchScore() {
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public void pointWon(int playerNumber) throws ExceptionScore {
        if (!this.getState().equals(ONGOING)) {
            throw new ExceptionScore("Матч закончен");
        }
        SetScore currentSet = getSetList().get(currentNumberSet);

        if (currentSet.getState().equals(TIE_BREAK)) {
            tieBreak.pointWon(playerNumber);
            if (tieBreak.getState().equals(PLAYER_TWO_WON) || tieBreak.getState().equals(PLAYER_ONE_WON)) {
                currentSet.setState(tieBreak.getState());
            }
        } else {
            gameScore.pointWon(playerNumber);

            if (gameScore.getState().equals(PLAYER_TWO_WON) || gameScore.getState().equals(PLAYER_ONE_WON)) {
                gameScore = new GameScore();
                currentSet.pointWon(playerNumber);
            }
        }

        if (currentSet.getState().equals(PLAYER_TWO_WON) || currentSet.getState().equals(PLAYER_ONE_WON)) {

            this.setPlayerScore(currentSet.getState().ordinal(), this.getPlayerScore(currentSet.getState().ordinal()) + 1);

            if (currentNumberSet >= 1 && this.getPlayerScore(playerNumber) == 2) {

                this.setState(State.values()[playerNumber]);

            }

            currentNumberSet++;
            tieBreak = new TieBreak();
        }
    }


}
