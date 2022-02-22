package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Book;
import com.lpnu.swqm.domain.BookStore;
import com.lpnu.swqm.exceptions.IncorrectPriceListRangeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStoreServiceTest {

    private static final Logger log = LogManager.getLogger(BookStoreServiceTest.class.getName());

    @BeforeClass
    public void before() {
        log.info("[{}] Starting test cases run...", getClass().getName());
    }

    @AfterClass
    public void after() {
        log.info("[{}] Test cases run finished!", getClass().getName());
    }

    @DataProvider(name = "priceListDataProvider")
    public Object[][] priceListDataProvider() {
        List<Book> books = Arrays.asList(
                new Book(Collections.emptyList(), null, null, 2022, Book.Category.FICTION),
                new Book(Collections.emptyList(), null, null, 2022, Book.Category.SCIENCE),
                new Book(Collections.emptyList(), null, null, 2022, Book.Category.SCIENCE)
        );
        Map<Float, Book> priceList = new HashMap<>();
        priceList.put(25.65F, books.get(0));
        priceList.put(43.12F, books.get(1));
        priceList.put(18.73F, books.get(2));

        Object[][] data = new Object[1][1];
        data[0][0] = new BookStore("testStore", priceList);

        return data;
    }

    @Test(dataProvider = "priceListDataProvider")
    public void testFindByPriceRangeSuccess(BookStore bookStore) {
        BookStoreService bookStoreService = new BookStoreService();
        bookStoreService.setBookStore(bookStore);
        final float min = 19.3F;
        final float max = 53.04F;
        Map<Float, Book> filteredPriceList = bookStoreService.findByPriceRange(min, max);

        Assert.assertNotNull(bookStoreService.getBookStore());
        Assert.assertEquals(bookStoreService.getBookStore().getTitle(), "testStore");
        Assert.assertEquals(bookStoreService.getBookStore(), bookStore);
        Assert.assertFalse(filteredPriceList.isEmpty());
        Assert.assertEquals(filteredPriceList.size(), 2);
    }

    @Test
    public void testFindByPriceRangeWarning() {
        BookStoreService bookStoreService = new BookStoreService();
        bookStoreService.setBookStore(new BookStore("emptyStore", new HashMap<>()));
        final float min = 19.3F;
        final float max = 53.04F;
        Map<Float, Book> filteredPriceList = bookStoreService.findByPriceRange(min, max);

        Assert.assertEquals(filteredPriceList, Collections.emptyMap());
    }

    @Test(expectedExceptions = IncorrectPriceListRangeException.class)
    public void testFindByPriceRangeException() {
        BookStoreService bookStoreService = new BookStoreService();
        bookStoreService.setBookStore(new BookStore("emptyStore", new HashMap<>()));
        final float min = 53.04F;
        final float max = 19.3F;
        bookStoreService.findByPriceRange(min, max);
    }

    @Test(dataProvider = "priceListDataProvider")
    public void testFindByPriceRangeOutOfPrices(BookStore bookStore) {
        BookStoreService bookStoreService = new BookStoreService();
        bookStoreService.setBookStore(bookStore);
        final float min = 15.04F;
        final float max = 17.62F;
        Map<Float, Book> filteredPriceList = bookStoreService.findByPriceRange(min, max);

        Assert.assertTrue(filteredPriceList.isEmpty());
    }
}