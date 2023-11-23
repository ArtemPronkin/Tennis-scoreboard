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
}
