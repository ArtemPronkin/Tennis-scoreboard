
import Controller.ServiceMatch.DAO.PlayerDAO;
import Controller.ServiceMatch.MatchesDTO.PageMatchesDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(PlayerDAO.getByName("А. ЗВЕРЕВ").get().getName());
        List<PageMatchesDTO> list = PageMatchesDTO.getPageByName(1,1,"А. ЗВЕРЕВ");
        for (PageMatchesDTO match:
             list) {
            System.out.println(match.getPlayer1());
            System.out.println(match.getPlayer2());
            System.out.println(match.getWinner());
            System.out.println(match.getTotalCount());
        }
    }
}
