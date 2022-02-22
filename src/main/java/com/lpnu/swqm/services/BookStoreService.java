package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Book;
import com.lpnu.swqm.domain.BookStore;
import com.lpnu.swqm.exceptions.IncorrectPriceListRangeException;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class BookStoreService {

    private static final Logger log = LogManager.getLogger(BookStoreService.class.getName());

    private BookStore bookStore;

    public Map<Float, Book> findByPriceRange(float min, float max) {
        if (min > max) {
            throw new IncorrectPriceListRangeException("Min price-list value should be less, than Max");
        } else if (bookStore.getPriceList().isEmpty()) {
            log.warn("Price-list is empty");
            return Collections.emptyMap();
        }

        return bookStore.getPriceList().entrySet().stream()
                .filter(priceListEntry -> priceListEntry.getKey() >= min && priceListEntry.getKey() <= max)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
