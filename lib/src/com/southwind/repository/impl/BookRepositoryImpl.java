package com.southwind.repository.impl;

import com.southwind.entity.Book;
import com.southwind.entity.BookCase;
import com.southwind.repository.BookRepository;
import com.southwind.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> finAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT * FROM book,bookcase WHERE bookcase.id =book.bookcaseid ORDER BY book.id LIMIT ?,? ;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
           statement = connection.prepareStatement(sql);
           statement.setInt(1,index);
           statement.setInt(2,limit);
           resultSet = statement.executeQuery();
           while (resultSet.next()) {
               books.add(
                       new Book(
                       resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getString(4),
                       resultSet.getInt(5),
                       resultSet.getDouble(6),
                       new BookCase(resultSet.getInt(9), resultSet.getString(10))
                       )
               );
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return books;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT count(*) FROM book,bookcase WHERE bookcase.id =book.bookcaseid;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count;
    }
}
