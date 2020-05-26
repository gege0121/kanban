package com.kanban.repository;

import com.kanban.model.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDaoImpl implements RatingDao{

    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Rating> get() {
        Session session = sessionFactory.openSession();
        List<Rating> r = session.createQuery("from Rating").list();
        session.close();
        return r;
    }

    @Override
    public Rating getById(long id) {
        Session session = sessionFactory.openSession();
        Query query = (Query) session.createQuery("from Rating where id=:id");
        query.setParameter("id",id);
        Rating r = (Rating) query.getSingleResult();
        session.close();
        return r;
    }

    @Override
    public Rating save(Rating Rating) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(Rating);
            transaction.commit();
            session.close();
            return  Rating;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save Rating\n",e);
            return null;
        }
    }

    @Override
    public int deleteById(long id) {
        String hql = "delete from Rating where id=:id";
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
    public Rating update(Rating Rating) {
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(Rating);
            transaction.commit();
            session.close();
            return Rating;
        }catch (Exception e){
            session.close();
            logger.error("Fail to update\n",e);
            return null;
        }
    }

    @Override
    public Rating checkExist(long userId, long cardId) {
        try{
            Session session = sessionFactory.openSession();
            Query query = (Query) session.createQuery("from Rating R where R.user.id=:id and R.card.id=:id2");
            query.setParameter("id",userId);
            query.setParameter("id2",cardId);
            Rating r = (Rating) query.getSingleResult();
            session.close();
            return r;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Rating saveOrUpdate(Rating Rating) {
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(Rating);
            transaction.commit();
            session.close();
            return Rating;
        }catch (Exception e){
            session.close();
            logger.error("Fail to saveOrUpdate\n",e);
            return null;
        }
    }
}
