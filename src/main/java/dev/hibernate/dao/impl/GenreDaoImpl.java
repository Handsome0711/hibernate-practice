package dev.hibernate.dao.impl;

import dev.hibernate.dao.GenreDao;
import dev.hibernate.exception.DataProcessingException;
import dev.hibernate.lib.Dao;
import dev.hibernate.model.Genre;
import dev.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre add(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long id = (Long) session.save(genre);
            transaction.commit();
            genre.setId(id);
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Genre entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
