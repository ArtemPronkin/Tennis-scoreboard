package app.serviceMatch.matchesScoreCalculations;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    @Getter
    private State state = State.ONGOING;
    private final List<T> playersScore = new ArrayList<>();

    protected abstract T getZeroScore();

    public void setState(State state) {
        this.state = state;
    }

    public Score(){
        playersScore.add(getZeroScore());
        playersScore.add(getZeroScore());
    }
    public  T getPlayerScore(int playerNumber){
        return playersScore.get(playerNumber);
    }
    public  T getOppositePlayerScore(int playerNumber){
        return playersScore.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScore(int playerNumber , T score){
       playersScore.set(playerNumber,score);
    }
    public void setOppositePlayerScore(int playerNumber , T score){
        playersScore.set(playerNumber == 0 ? 1 : 0,score);
    }
    abstract void pointWon(int playerNumber) throws ExceptionScore;
}
