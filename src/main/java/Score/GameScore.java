package Score;

public class GameScore extends Score<GameState>{
    @Override
    protected GameState getZeroScore() {
        return GameState.ZERO;
    }

    @Override
    public void pointWon(int playerNumber) throws ExceptionScore {
        if (!getState().equals(State.ONGOING)){
            throw new ExceptionScore("Гейм закончен");
        }

        var currentScorePlayer = this.getPlayerScore(playerNumber);
        var oppositeScorePlayer = this.getOppositePlayerScore(playerNumber);

        if (currentScorePlayer.equals(GameState.ADVANTAGE)){
            this.setState(State.values()[playerNumber]);
            return;
        }

        if (currentScorePlayer.equals(GameState.FOURTY) && oppositeScorePlayer.ordinal()<=2){
            this.setState(State.values()[playerNumber]);
            return;
        }

        if (currentScorePlayer.equals(GameState.FOURTY) && oppositeScorePlayer.equals(GameState.ADVANTAGE)){
            setOppositePlayerScore(playerNumber, GameState.FOURTY);
            return;
        }

        setPlayerScore(playerNumber,getPlayerScore(playerNumber).next());

    }
}
