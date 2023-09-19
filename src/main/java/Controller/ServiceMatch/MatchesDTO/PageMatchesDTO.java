package Controller.ServiceMatch.MatchesDTO;

import Controller.Utils.SessionFactoryUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class PageMatchesDTO {
    @Id
    private Long id;
    @Column (name = "player1")
    private String Player1;
    @Column (name = "player2")
    private String Player2;
    @Column (name = "winner")
    private String winner;
    @Column (name = "TotalCount")
    private int TotalCount;


    public PageMatchesDTO(String player1, String player2, String winner,String player) {
        Player1 = player1;
        Player2 = player2;
        this.winner = winner;
    }

    public static List<PageMatchesDTO> getPageByName(int pageNumber, int pageSize ,String player) {
        String query = """
                select id,Player1,Player2,winner, Count(*)   Over () AS TotalCount from
                (
                (SELECT m.id id, P.name Player1 , p2.name Player2 , p3.name winner  from MATCHES M
                
                    join Players P on P.ID = M.PLAYER_1 and p.NAME = :name
                    join Players P2 on P2.ID = M.PLAYER_2
                    join Players P3 on P3.ID = M.winner)
                
                UNION
                
                (SELECT m.id id, P.name Player1 , p2.name Player2 , p3.name winner
                from MATCHES M
                join Players P2 on P2.ID = M.PLAYER_2 and P2.NAME = :name
                join Players P on P.ID = M.PLAYER_1
                join Players P3 on P3.ID = M.winner)
                )
                Offset (:pageNumber - 1) * :pagaSize Rows
                    Fetch Next :pagaSize Rows Only;
                """;

        Transaction transaction = null;
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
        List<PageMatchesDTO> list = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            var nativeQuery= session.createNativeQuery(query, PageMatchesDTO.class);
            nativeQuery.setParameter("pagaSize",pageSize);
            nativeQuery.setParameter("pageNumber",pageNumber);
            nativeQuery.setParameter("name",player);

            list =  nativeQuery.getResultList();

        }catch (RuntimeException e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }finally {
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

    public static List<PageMatchesDTO> getPage (int pageNumber, int pageSize){
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
            var nativeQuery= session.createNativeQuery(query, PageMatchesDTO.class);
            nativeQuery.setParameter("pagaSize",pageSize);
            nativeQuery.setParameter("pageNumber",pageNumber);
            list =  nativeQuery.getResultList();

        }catch (RuntimeException e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
        }
        return list;
    }

//    public DTOMatch (Match match){
//        winner = match.getWinner().getName();
//        if (match.getPlayer1().equals(match.getWinner())){
//            loose = match.getPlayer2().getName();
//        }else {loose = match.getPlayer1().getName();}
//    }
//    public static List<DTOMatch> getDTOMatchlist(List<Match> listEntity){
//        List<DTOMatch> listDTO = new ArrayList<>();
//        for (Match match:
//             listEntity) {
//            listDTO.add(new DTOMatch(match));
//        }
//        return listDTO;
//    }

}
