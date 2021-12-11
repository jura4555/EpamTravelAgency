package com.fedynets.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO interface
 * @author Yurii Fedynets
 */
public interface Dao<T> {

    boolean add(Connection connection, T t) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    boolean update(Connection connection, T t) throws SQLException;

}