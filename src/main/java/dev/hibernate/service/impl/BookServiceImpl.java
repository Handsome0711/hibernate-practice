package dev.hibernate.service.impl;

import dev.hibernate.dao.BookDao;
import dev.hibernate.lib.Inject;
import dev.hibernate.lib.Service;
import dev.hibernate.model.Author;
import dev.hibernate.model.Book;
import dev.hibernate.model.Genre;
import dev.hibernate.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return bookDao.getByAuthor(author);
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        return bookDao.getByGenre(genre);
    }
}
