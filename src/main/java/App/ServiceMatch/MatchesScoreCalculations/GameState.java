package App.ServiceMatch.MatchesScoreCalculations;

public enum GameState {
    ZERO("0"),FIFTEEN("15"),THIRTY("30"),FOURTY("40"),ADVANTAGE("AD");
    private String count;
    GameState(String number) {
        this.count=number;
    }


    public GameState next() throws ExceptionScore {
        return  GameState.values()[this.ordinal() + 1];
    }

    @Override
    public String toString() {
        return count;
    }
}
