package App.ServiceMatch.MatchesScoreCalculations;

import static App.ServiceMatch.MatchesScoreCalculations.State.ONGOING;

public class TieBreak extends Score<Integer> {

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public void pointWon(int playerNumber) throws ExceptionScore {

        if (!getState().equals(ONGOING)){
            throw new ExceptionScore("Тай-брейк закончен");
        }

        var currentScorePlayer = this.getPlayerScore(playerNumber);
        var oppositeScorePlayer = this.getOppositePlayerScore(playerNumber);

        if (currentScorePlayer >= 6 && currentScorePlayer > oppositeScorePlayer){
                this.setState(State.values()[playerNumber]);
        }
        else this.setPlayerScore(playerNumber,currentScorePlayer + 1);
    }
}
