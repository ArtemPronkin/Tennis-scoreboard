package App.ServiceMatch.Matches;

import App.ServiceMatch.Player.PlayerDAO;
import App.ServiceMatch.MatchesScoreCalculations.MatchScore;
import App.ServiceMatch.MatchesScoreCalculations.State;
import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class GameRepository {
    private static HashMap<UUID, GameRepository> INSTANCE = new HashMap<>();

    private String player1;
    private String player2;
    private MatchScore matchScore;

    public GameRepository(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = new MatchScore();
    }

    public static MatchScore getMatch(UUID uuid){
        return INSTANCE.get(uuid).getMatchScore();
    }
    public static String getPlayer1(UUID uuid){
        if (INSTANCE.containsKey(uuid))return INSTANCE.get(uuid).getPlayer1();
        else return null;
    }
    public static String getPlayer2(UUID uuid){
        if (INSTANCE.containsKey(uuid))return INSTANCE.get(uuid).getPlayer2();
        else return null;
    }
    public static UUID createMatch(String player1,String player2){
        var uuid = UUID.randomUUID();
        INSTANCE.put(uuid,new GameRepository(player1,player2));
        return uuid;
    }
    public static boolean haveMatch(UUID uuid){
        return INSTANCE.containsKey(uuid);
    }
    public static void deleteMatch(UUID uuid){
        INSTANCE.remove(uuid);
    }
    public static synchronized void endMatch(UUID uuid){
        if(!haveMatch(uuid)){
            return;
        }
        var Player1  = PlayerDAO.getByName(GameRepository.getPlayer1(uuid));
        var Player2  = PlayerDAO.getByName(GameRepository.getPlayer2(uuid));

        if (getMatch(uuid).getState().equals(State.PLAYER_ONE_WON)){
            MatchDAO.create(new Match(Player1.get(),Player2.get(),Player1.get()));
        }
        if (getMatch(uuid).getState().equals(State.PLAYER_TWO_WON)){
            MatchDAO.create(new Match(Player1.get(),Player2.get(),Player2.get()));
        }
        GameRepository.deleteMatch(uuid);
    }
}
