package app.serviceMatch.player;


import app.utils.SessionFactoryUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;


public class PlayerDAO {

    public static void create (Player player){
        Transaction transaction = null;
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
        try {
           transaction = session.beginTransaction();
           session.persist(player);
           transaction.commit();
    }catch (RuntimeException e){
        if (transaction!=null){
            transaction.rollback();
            System.out.println("Игрок уже есть в таблице");
        }
        }finally {
            session.close();
        }
    }
    public static Optional<Player>getByName (String name){
        Optional<Player> player = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            player = Optional.ofNullable((Player) query.getSingleResult()) ;
            session.getTransaction().commit();
        }
        return player;
    }
}
