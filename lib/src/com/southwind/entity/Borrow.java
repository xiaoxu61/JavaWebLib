package com.southwind.entity;

public class Borrow {
    private Integer id;
    private Book book;
    private String borrowTime;
    private String returnTime;
    private Reader reader;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Borrow(Integer id, Book book, String borrowTime, String returnTime, Reader reader, Integer state) {
        this.id = id;
        this.book = book;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.reader = reader;
        this.state = state;
    }
}
