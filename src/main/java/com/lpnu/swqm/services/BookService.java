package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Book;
import com.lpnu.swqm.exceptions.BooksNotGivenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService {

    private static final Logger log = LogManager.getLogger(BookService.class.getName());

    public List<Book> filterByCategory(List<Book> books, Book.Category filter) {
        if (Objects.isNull(books)) {
            throw new BooksNotGivenException("Null passed instead of books");
        } else if (books.isEmpty()) {
            log.warn("No books given! An empty list will be returned.");
            return Collections.emptyList();
        }

        return books.stream()
                .filter(book -> book.getCategory().equals(filter))
                .collect(Collectors.toList());
    }
}
