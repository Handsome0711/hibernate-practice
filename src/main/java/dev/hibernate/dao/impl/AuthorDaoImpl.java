package dev.hibernate.dao.impl;

import dev.hibernate.dao.AuthorDao;
import dev.hibernate.exception.DataProcessingException;
import dev.hibernate.lib.Dao;
import dev.hibernate.model.Author;
import dev.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long id = (Long) session.save(author);
            transaction.commit();
            author.setId(id);
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
