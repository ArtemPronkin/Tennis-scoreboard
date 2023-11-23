package app.serviceMatch.matchesScoreCalculations;

import static app.serviceMatch.matchesScoreCalculations.State.ONGOING;

public class TieBreakScore extends Score<Integer> {

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public void pointWon(int playerNumber) throws ScoreException {

        if (!getState().equals(ONGOING)) {
            throw new ScoreException("Тай-брейк закончен");
        }

        var currentScorePlayer = this.getPlayerScore(playerNumber);
        var oppositeScorePlayer = this.getOppositePlayerScore(playerNumber);

        if (currentScorePlayer >= 6 && currentScorePlayer > oppositeScorePlayer) {
            this.setState(State.values()[playerNumber]);
        } else this.setPlayerScore(playerNumber, currentScorePlayer + 1);
    }
}
