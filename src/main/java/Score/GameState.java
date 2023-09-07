package Score;

public enum GameState {
    ZERO,FIFTEEN,THIRTY,FOURTY,ADVANTAGE;

    public GameState next() throws ExceptionScore {
        return  GameState.values()[this.ordinal() + 1];
    }

}
