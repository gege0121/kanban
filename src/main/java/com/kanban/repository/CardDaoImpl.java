package com.kanban.repository;

import com.kanban.model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CardDaoImpl implements CardDao{
    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Card> get() {
        Session session = sessionFactory.openSession();
        List<Card> r = session.createQuery("from Card").list();
        session.close();
        return r;
    }

    @Override
    public List<Card> getFetchAll() {
        Session session = sessionFactory.openSession();
        List<Card> r = session.createQuery("from Card C left join fetch C.ratings left join fetch C.comments Com left join fetch Com.user").list();
        session.close();
        return r;
    }

    @Override
    public Card getById(long id) {
        Session session = sessionFactory.openSession();
        Query query = (Query) session.createQuery("from Card where id=:id");
        query.setParameter("id",id);
        Card r = (Card) query.getSingleResult();
        session.close();
        return r;
    }

    @Override
    public Card save(Card Card) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(Card);
            transaction.commit();
            session.close();
            return  Card;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save Card\n",e);
            return null;
        }
    }

    @Override
    public int deleteById(long id) {
        String hql = "delete from Card where id=:id";
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery(hql);
            query.setParameter("id",id);
            int r = query.executeUpdate();
            transaction.commit();
            session.close();
            return r;
        }catch (Exception e){
            session.close();
            logger.error("Fail to delete by id\n",e);
            return -1;
        }
    }

    @Override
    public Card update(Card Card) {
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(Card);
            transaction.commit();
            session.close();
            return Card;
        }catch (Exception e){
            session.close();
            logger.error("Fail to update\n",e);
            return null;
        }
    }


}
