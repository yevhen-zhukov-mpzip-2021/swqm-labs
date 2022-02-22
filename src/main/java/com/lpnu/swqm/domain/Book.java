package com.lpnu.swqm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Book {

    private List<String> authors;
    private String title;
    private String editionName;
    private int editionYear;
    private Category category;

    public enum Category {
        SCIENCE, FICTION
    }
}
