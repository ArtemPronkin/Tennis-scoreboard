package app.serviceMatch.matchesScoreCalculations;

import static app.serviceMatch.matchesScoreCalculations.State.*;

public class SetScore extends Score<Integer> {
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public void pointWon(int playerNumber) throws ExceptionScore {

        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        var currentScorePlayer = this.getPlayerScore(playerNumber);
        var oppositeScorePlayer = this.getOppositePlayerScore(playerNumber);

        if (currentScorePlayer == 8) {
            throw new ExceptionScore("Сет закончен");
        } else if (currentScorePlayer == 6 && oppositeScorePlayer <= 4) {
            setState(State.values()[playerNumber]);
        } else if (currentScorePlayer == 6 && oppositeScorePlayer == 6) {
            setState(TIE_BREAK);
        } else if (currentScorePlayer == 7 && oppositeScorePlayer <= 5) {
            setState(State.values()[playerNumber]);
        }

    }
}
