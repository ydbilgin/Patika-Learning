package dev.patika.library.business.abstracts;

import dev.patika.library.entities.Author;
import dev.patika.library.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);
    Book update(Book book);
    Book get(int id);
    Page<Book> cursor(int page, int pageSize);
    boolean delete(int id);
}
