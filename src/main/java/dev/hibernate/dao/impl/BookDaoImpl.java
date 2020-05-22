package dev.hibernate.dao.impl;

import dev.hibernate.dao.BookDao;
import dev.hibernate.exception.DataProcessingException;
import dev.hibernate.lib.Dao;
import dev.hibernate.model.Author;
import dev.hibernate.model.Book;
import dev.hibernate.model.Genre;
import dev.hibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BookDaoImpl implements BookDao {

    @Override
    public Book add(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long id = (Long) session.save(book);
            transaction.commit();
            book.setId(id);
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Book entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root).where(cb.equal(root.get("title"), title));
            return session.createQuery(cr).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving book by title", e);
        }
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root).where(cb.isMember(author, root.get("authors")));
            return session.createQuery(cr).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving book by author", e);
        }
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root).where(cb.equal(root.get("genre"), genre));
            return session.createQuery(cr).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving book by genre", e);
        }
    }
}
