package com.fedynets.service;

import com.fedynets.constants.UserRole;
import com.fedynets.dao.UserDao;
import com.fedynets.entity.User;
import com.fedynets.util.DBManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    static final Logger LOG = LogManager.getLogger(UserService.class);

    public UserService(){
    }

    public boolean addUser(User user) {
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        userDao.add(connection, user);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public List<User> findAllUser(){
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return userList;
    }

    public boolean deleteUser(String name){
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        userDao.delete(connection, name);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public boolean updateUser(User user) {
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        userDao.update(connection, user);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public User findUserByLogin(String login){
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        User user = userDao.findUserByLogin(connection, login);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return user;
    }

    public List<User> findUserByRole(UserRole userRole) {
        Connection connection = DBManager.getInstance().getConnection();
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findUserByRole(connection, userRole);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return userList;
    }

}
