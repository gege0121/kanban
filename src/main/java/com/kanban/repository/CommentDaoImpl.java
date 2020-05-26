package com.kanban.repository;

import com.kanban.model.Comment;
import com.kanban.model.Role;
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
public class CommentDaoImpl implements CommentDao{
    @Autowired
    private SessionFactory sessionFactory;
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Comment> get() {
        Session session = sessionFactory.openSession();
        List<Comment> r = session.createQuery("from Comment").list();
        session.close();
        return r;
    }

    @Override
    public Comment getById(long id) {
        Session session = sessionFactory.openSession();
        Query query = (Query) session.createQuery("from Comment where id=:id");
        query.setParameter("id",id);
        Comment r = (Comment) query.getSingleResult();
        session.close();
        return r;
    }

    @Override
    public Comment save(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction=session.beginTransaction();
            long r = (long) session.save(comment);
            transaction.commit();
            session.close();
            return  comment;
        }catch (Exception e){
            session.close();
            logger.error("Fail to save comment\n",e);
            return null;
        }
    }

    @Override
    public int deleteById(long id) {
        String hql = "delete from Comment where id=:id";
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
    public Comment update(Comment comment) {
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(comment);
            transaction.commit();
            session.close();
            return comment;
        }catch (Exception e){
            session.close();
            logger.error("Fail to update\n",e);
            return null;
        }
    }
}
