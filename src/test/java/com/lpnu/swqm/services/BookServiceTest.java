package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Book;
import com.lpnu.swqm.exceptions.BooksNotGivenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookServiceTest {

    private static final Logger log = LogManager.getLogger(BookServiceTest.class.getName());

    private final BookService bookService = new BookService();

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "booksDataProvider")
    public Object[][] booksDataProvider() {
        return new Object[][]{
                {
                        new Book(Collections.emptyList(), null, null, 2022, Book.Category.FICTION),
                        new Book(Collections.emptyList(), null, null, 2022, Book.Category.SCIENCE),
                        new Book(Collections.emptyList(), null, null, 2022, Book.Category.SCIENCE)
                }
        };
    }

    @Test(dataProvider = "booksDataProvider")
    public void testFilterByCategory(Book[] books) {

        List<Book> bookList = Arrays.asList(books);
        List<Book> actual = bookService.filterByCategory(bookList, Book.Category.SCIENCE);
        int expectedSize = 2;

        Assert.assertNotNull(actual);
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(actual.size(), expectedSize);
    }

    @Test(expectedExceptions = BooksNotGivenException.class)
    public void testFilterByCategoryNullBooks() {
        bookService.filterByCategory(null, Book.Category.SCIENCE);
    }

    @Test
    public void testFilterByCategoryFailBooksListIsEmpty() {
        List<Book> actual = bookService.filterByCategory(Collections.emptyList(), Book.Category.SCIENCE);

        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.isEmpty());
    }

    @Test(dataProvider = "booksDataProvider")
    public void testNonCategoryGetters(Book[] books) {

        Book actual = books[0];
        int expectedEditionYear = 2022;

        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.getAuthors().isEmpty());
        Assert.assertNull(actual.getTitle());
        Assert.assertNull(actual.getEditionName());
        Assert.assertEquals(actual.getEditionYear(), expectedEditionYear);
    }
}