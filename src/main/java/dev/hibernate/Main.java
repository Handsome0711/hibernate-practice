package dev.hibernate;

import dev.hibernate.lib.Injector;
import dev.hibernate.model.Author;
import dev.hibernate.model.Book;
import dev.hibernate.model.Genre;
import dev.hibernate.service.AuthorService;
import dev.hibernate.service.BookService;
import dev.hibernate.service.GenreService;
import java.util.List;

public class Main {
    public static final Injector INJECTOR = Injector.getInstance("dev.hibernate");

    public static void main(String[] args) {
        AuthorService authorService = (AuthorService)
                INJECTOR.getInstance(AuthorService.class);
        Author shevchenko = new Author();
        shevchenko.setName("Taras");
        shevchenko.setSurname("Shevchenko");
        authorService.add(shevchenko);

        Author kostenko = new Author();
        kostenko.setName("Lina");
        kostenko.setSurname("Kostenko");
        authorService.add(kostenko);

        GenreService genreService = (GenreService)
                INJECTOR.getInstance(GenreService.class);
        Genre drama = new Genre();
        drama.setName("drama");
        genreService.add(drama);
        Genre poem = new Genre();
        poem.setName("poem");
        genreService.add(poem);

        Book kobzar = new Book();
        kobzar.setTitle("Kobzar");
        kobzar.setAuthors(List.of(shevchenko));
        kobzar.setGenre(poem);
        BookService bookService = (BookService)
                INJECTOR.getInstance(BookService.class);
        bookService.add(kobzar);

        Book book = new Book();
        book.setTitle("Some");
        book.setGenre(poem);
        book.setAuthors(List.of(shevchenko, kostenko));
        bookService.add(book);

        System.out.println(bookService.getByTitle("Kobzar"));
        bookService.getByAuthor(shevchenko).forEach(System.out::println);
        bookService.getByGenre(poem).forEach(System.out::println);
    }
}
