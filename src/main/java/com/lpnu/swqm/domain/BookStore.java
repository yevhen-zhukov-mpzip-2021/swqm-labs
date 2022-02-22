package com.lpnu.swqm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class BookStore {

    private String title;
    private Map<Float, Book> priceList;
}
