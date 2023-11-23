package app.serviceMatch.matches;

import app.utils.SessionFactoryUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@NoArgsConstructor
public class PageMatchesDTO {
    @Id
    private Long id;
    @Column(name = "player1")
    private String Player1;
    @Column(name = "player2")
    private String Player2;
    @Column(name = "winner")
    private String winner;
    @Column(name = "TotalCount")
    private int TotalCount;


    public PageMatchesDTO(String player1, String player2, String winner, String player) {
        Player1 = player1;
        Player2 = player2;
        this.winner = winner;
    }

    public static List<PageMatchesDTO> getPageByName(int pageNumber, int pageSize, String player) {
        String query = """
                select m.id id, P.name Player1 , p2.name Player2 , p3.name winner,Count(*)   Over () AS TotalCount
                from (
                select * from MATCHES
                where player_1 = (select id  from PLAYERS p4 where name = :name)
                   or player_2 = (select id  from PLAYERS p4 where name = :name)
                ) as M
                    join Players P on P.ID = M.PLAYER_1
                    join Players P2 on P2.ID = M.PLAYER_2
                    join Players P3 on P3.ID = M.winner
                Offset (:pageNumber - 1) * :pagaSize Rows
                    Fetch Next :pagaSize Rows Only ;
                """;

        Transaction transaction = null;
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
        List<PageMatchesDTO> list = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            var nativeQuery = session.createNativeQuery(query, PageMatchesDTO.class);
            nativeQuery.setParameter("pagaSize", pageSize);
            nativeQuery.setParameter("pageNumber", pageNumber);
            nativeQuery.setParameter("name", player);

            list = nativeQuery.getResultList();

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return list;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static List<PageMatchesDTO> getPage(int pageNumber, int pageSize) {
        String query = """
                SELECT m.id id, P.name Player1 , p2.name Player2 , p3.name winner ,Count(*)   Over () AS TotalCount from Matches M join Players P on P.ID = M.PLAYER_1
                                                                             join Players P2 on P2.ID = M.PLAYER_2
                                                                             join Players P3 on P3.ID = M.winner
                Offset (:pageNumber - 1) * :pagaSize Rows
                    Fetch Next :pagaSize Rows Only;
                """;

        Transaction transaction = null;
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
        List<PageMatchesDTO> list = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            var nativeQuery = session.createNativeQuery(query, PageMatchesDTO.class);
            nativeQuery.setParameter("pagaSize", pageSize);
            nativeQuery.setParameter("pageNumber", pageNumber);
            list = nativeQuery.getResultList();

        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return list;
    }
}
