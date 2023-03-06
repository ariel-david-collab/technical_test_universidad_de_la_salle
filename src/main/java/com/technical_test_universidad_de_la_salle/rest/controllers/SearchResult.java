package com.technical_test_universidad_de_la_salle.rest.controllers;

public class SearchResult {

    private String searchword;
    private int rows;
    private String word;
    private boolean contains;
    private int start_row;
    private int start_col;

    public SearchResult(
            String searchword,
            int rows,
            String word,
            boolean contains,
            int start_row,
            int start_col) {
        this.searchword = searchword;
        this.rows = rows;
        this.word = word;
        this.contains = contains;
        this.start_row = start_row;
        this.start_col = start_col;
    }

    public SearchResult(
            String searchword,
            int rows,
            String word,
            boolean contains) {
        this(
                searchword,
                rows,
                word,
                contains,
                -1,
                -1);
    }

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isContains() {
        return contains;
    }

    public void setContains(boolean contains) {
        this.contains = contains;
    }

    public int getStart_row() {
        return start_row;
    }

    public void setStart_row(int start_row) {
        this.start_row = start_row;
    }

    public int getStart_col() {
        return start_col;
    }

    public void setStart_col(int start_col) {
        this.start_col = start_col;
    }

}
