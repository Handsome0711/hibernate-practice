package dev.hibernate.service;

import dev.hibernate.model.Author;
import dev.hibernate.model.Book;
import dev.hibernate.model.Genre;
import java.util.List;

public interface BookService {
    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getByAuthor(Author author);

    List<Book> getByGenre(Genre genre);
}
