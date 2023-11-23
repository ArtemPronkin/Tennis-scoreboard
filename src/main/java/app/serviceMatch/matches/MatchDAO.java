package app.serviceMatch.matches;

import app.utils.SessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;



@NoArgsConstructor
public class MatchDAO {

    public static void create(Match match) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }
//    public static Optional<Match> getById(int id) {
//        Optional<Match>match = Optional.empty();
//        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
//            session.beginTransaction();
//            match = Optional.ofNullable(session.get(Match.class, id));
//            session.getTransaction().commit();
//        }
//        return match;
//    }
//    public static List<Match> getAll() {
//        List<Match> matches = null;
//        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
//            session.beginTransaction();
//            matches = session.createQuery("FROM Matches ").getResultList();
//            session.getTransaction().commit();
//        }
//        return matches;
//    }


}
