package Controller.Utils;

import Score.MatchScore;
import lombok.Data;

import java.util.HashMap;
import java.util.UUID;

@Data
public class GameRepository {
    private static HashMap<UUID, GameRepository> games= new HashMap<>();

    private String player1;
    private String player2;
    private MatchScore matchScore;

    public GameRepository(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = new MatchScore();
    }

    public static MatchScore getMatch(UUID uuid){
        return games.get(uuid).getMatchScore();
    }
    public static String getPlayer1(UUID uuid){
        return games.get(uuid).getPlayer1();
    }
    public static String getPlayer2(UUID uuid){
        return games.get(uuid).getPlayer2();
    }
    public static UUID createMatch(String player1,String player2){
        var uuid= UUID.randomUUID();
        games.put(uuid,new GameRepository(player1,player2));
        return uuid;
    }
    public static boolean haveMatch(UUID uuid){
        return games.containsKey(uuid);
    }
}
