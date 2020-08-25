package com.southwind.repository.impl;

import com.southwind.entity.Book;
import com.southwind.entity.Borrow;
import com.southwind.entity.Reader;
import com.southwind.repository.BorrowRepository;
import com.southwind.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,0);";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,bookid);
            statement.setInt(2,readerid);
            statement.setString(3,borrowtime);
            statement.setString(4,returntime);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }

    }

    @Override
    public List<Borrow> findAllByReaderId(Integer readerid,Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT br.id, b.name, b.author, b.publish, br.borrowtime, br.returntime," +
                "r.name, r.tel, r.cardid, br.state FROM book b, borrow br,reader r " +
                "WHERE readerid = ? AND br.readerid = r.id AND br.bookid = b.id limit ?,?;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,readerid);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add( new Borrow(
                        resultSet.getInt(1),
                        new Book(resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getInt(10)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow br,book b,reader r where readerid = ? " +
                    "and b.id = br.bookid and r.id = br.readerid;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,readerid);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count;
    }

    @Override
    public List<Borrow> findAllByState(Integer state,Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT br.id, b.name, b.author, b.publish, br.borrowtime, br.returntime," +
                "r.name, r.tel, r.cardid, br.state FROM book b, borrow br,reader r " +
                "WHERE state = ? AND br.readerid = r.id AND br.bookid = b.id limit ?,?;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add( new Borrow(
                        resultSet.getInt(1),
                        new Book(resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)),
                        resultSet.getInt(10)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public int countState(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "SELECT COUNT(*) FROM borrow WHERE state=?;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int countState = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                countState = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return countState;
    }

    @Override
    public void handle(Integer borrowId, Integer state, Integer adminId) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set state=?,adminid=? where id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,adminId);
            statement.setInt(3,borrowId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }
}
