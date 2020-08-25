package com.southwind.repository;

import com.southwind.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> finAll(int index, int limit);
    public int count();
}
