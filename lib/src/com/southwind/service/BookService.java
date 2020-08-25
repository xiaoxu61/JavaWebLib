package com.southwind.service;

import com.southwind.entity.Book;
import com.southwind.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public int getBorrowPages(Integer readerId);
    public void addBorrow(Integer bookId, Integer readerId);
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page);
    public List<Borrow> findAllBorrowByState(Integer state, Integer page);
    public int getBorrowPagesByState(Integer state);
    public void handleBorrow(Integer borrowId, Integer state, Integer adminId);

}
