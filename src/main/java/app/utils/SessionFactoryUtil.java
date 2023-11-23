package app.utils;

import app.serviceMatch.matches.MatchDAO;
import app.serviceMatch.player.PlayerDAO;
import app.serviceMatch.matches.Match;
import app.serviceMatch.player.Player;
import app.serviceMatch.matches.PageMatchesDTO;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    private org.hibernate.SessionFactory sessionFactory;

    private SessionFactoryUtil() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Match.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(PageMatchesDTO.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    private static class SingletonHolder {
        public static final SessionFactoryUtil HOLDER_INSTANCE = new SessionFactoryUtil();
    }

    public static org.hibernate.SessionFactory getSessionFactory() {
        System.out.println("Выдана сессия");
        return SingletonHolder.HOLDER_INSTANCE.sessionFactory;
    }

    static {
        Player player1 = new Player("К. АЛЬКАРАС");
        Player player2 = new Player("Ф. КОБОЛЛИ");
        PlayerDAO.create(player1);
        PlayerDAO.create(player2);
        MatchDAO.create(new Match(player1, player2, player1));
        MatchDAO.create(new Match(player1, player2, player1));
        MatchDAO.create(new Match(player1, player2, player1));
        MatchDAO.create(new Match(player1, player2, player1));
        MatchDAO.create(new Match(player1, player2, player1));

        Player player3 = new Player("Д. МЕДВЕДЕВ");
        Player player4 = new Player("Т. УАЙЛД");
        PlayerDAO.create(player3);
        PlayerDAO.create(player4);
        MatchDAO.create(new Match(player3, player4, player4));
        MatchDAO.create(new Match(player3, player4, player3));
        MatchDAO.create(new Match(player3, player4, player4));
        MatchDAO.create(new Match(player3, player4, player3));
        MatchDAO.create(new Match(player3, player4, player4));

        Player player5 = new Player("Н. ДЖОКОВИЧ");
        Player player6 = new Player("М. ФУЧОВИЧ");
        PlayerDAO.create(player5);
        PlayerDAO.create(player6);
        MatchDAO.create(new Match(player5, player6, player6));
        MatchDAO.create(new Match(player5, player6, player5));
        MatchDAO.create(new Match(player5, player6, player6));
        MatchDAO.create(new Match(player5, player6, player5));

        Player player7 = new Player("К. ХАЧАНОВ");
        PlayerDAO.create(player7);
        MatchDAO.create(new Match(player7, player5, player7));
        MatchDAO.create(new Match(player7, player5, player5));
        MatchDAO.create(new Match(player7, player5, player7));
        MatchDAO.create(new Match(player7, player5, player5));

        Player player8 = new Player("К. РУУД");
        Player player9 = new Player("А. ЗВЕРЕВ");
        PlayerDAO.create(player8);
        PlayerDAO.create(player9);
        MatchDAO.create(new Match(player8, player9, player8));
        MatchDAO.create(new Match(player8, player9, player9));
        MatchDAO.create(new Match(player8, player9, player8));
        MatchDAO.create(new Match(player8, player9, player9));
        MatchDAO.create(new Match(player8, player9, player8));

        Player player10 = new Player("Ф. ТИАФО");
        PlayerDAO.create(player10);
        MatchDAO.create(new Match(player3, player10, player10));
        MatchDAO.create(new Match(player3, player10, player3));
        MatchDAO.create(new Match(player3, player10, player10));
        MatchDAO.create(new Match(player3, player10, player3));
    }


}

