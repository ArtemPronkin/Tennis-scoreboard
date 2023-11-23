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

    private TieBreakScore tieBreakScore = new TieBreakScore();

    private List<SetScore> setList = Arrays.asList(new SetScore(), new SetScore(), new SetScore());


    public MatchScore() {
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public void pointWon(int playerNumber) throws ScoreException {
        if (!this.getState().equals(ONGOING)) {
            throw new ScoreException("Матч закончен");
        }
        SetScore currentSet = getSetList().get(currentNumberSet);

        if (currentSet.getState().equals(TIE_BREAK)) {
            tieBreakScore.pointWon(playerNumber);
            if (tieBreakScore.getState().equals(PLAYER_TWO_WON) || tieBreakScore.getState().equals(PLAYER_ONE_WON)) {
                currentSet.setState(tieBreakScore.getState());
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
            tieBreakScore = new TieBreakScore();
        }
    }


}
