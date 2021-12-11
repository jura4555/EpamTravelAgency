package com.fedynets.dao;

import com.fedynets.constants.UserRole;
import com.fedynets.entity.User;
import com.fedynets.util.DBManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class implements DAO interface and
 * implements all the methods needed to work with the database
 * Use singelton pattern
 */
public class UserDao implements Dao<User> {
    static final Logger LOG = LogManager.getLogger(UserDao.class);

    public UserDao(){
    }

    /**
     * Add user to the table user in database
     * Return true if the add operation was successful and false if SQLException occurred
     * @param connection
     * @param user
     * @return boolean
     */
    @Override
    public boolean add(Connection connection, User user) {
        int newId = 0;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.UserQuery.SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getPassword());
            prst.setString(3, user.getName());
            prst.setString(4, user.getSurname());
            prst.setString(5, user.getEmail());
            prst.setInt(6,user.getUserRole().getIndex());
            prst.setBoolean(7, user.isActive());
            int uId = prst.executeUpdate();
            if(uId == 1){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        newId = generatedKeys.getInt(1);
                        user.setUserId(newId);
                    }
                }
            }
        } catch(SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Return list of all user when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @return list of user
     */
    @Override
    public List<User> findAll(Connection connection) {
        List<User> userList = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery.UserQuery.SQL_SELECT_ALL)){
            while(rs.next()){
                User.Builder bUser = new User.Builder();
                bUser.bSetUserId(rs.getInt("user_id"));
                bUser.bSetLogin(rs.getString("login"));
                bUser.bSetPassword(rs.getString("user_password"));
                bUser.bSetName(rs.getString("user_name"));
                bUser.bSetSurname(rs.getString("user_surname"));
                bUser.bSetEmail(rs.getString("user_email"));
                bUser.bSetUserRole(UserRole.getUserRole(rs.getInt("user_role_id")));
                bUser.bSetActive(rs.getBoolean("is_active"));
                userList.add(bUser.getResult());
            }
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return userList;
    }

    /**
     * Delete user by login in the database
     * Return true if the delete operation was successful and false if SQLException occurred
     * @param connection
     * @param name
     * @return boolean
     */
    public boolean delete(Connection connection, String name){
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.UserQuery.SQL_DELETE_USER)){
            prst.setString(1, name);
            if(prst.executeUpdate() != 1){
                return false;
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Update user by login in the database
     * Return true if the update operation was successful and false if SQLException occurred
     * @param connection
     * @param user
     * @return boolean
     */
    @Override
    public boolean update(Connection connection, User user) {
        boolean result;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.UserQuery.SQL_UPDATE_USER_BY_ID)){
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getPassword());
            prst.setString(3, user.getName());
            prst.setString(4, user.getSurname());
            prst.setString(5, user.getEmail());
            prst.setInt(6,user.getUserRole().getIndex());
            prst.setBoolean(7, user.isActive());
            prst.setInt(8,user.getUserId());
            result = prst.executeUpdate() > 0;
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return result;
    }

    /**
     * Find user by login in the database
     * Return user when there are not exception
     * and null when happen SQLException
     * @param connection
     * @param login
     * @return user
     */
    public User findUserByLogin(Connection connection, String login) {
        User.Builder bUser = new User.Builder();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.UserQuery.SQL_SELECT_USER_BY_LOGIN)){
            prst.setString(1, login);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                bUser.bSetUserId(rs.getInt("user_id"));
                bUser.bSetLogin(rs.getString("login"));
                bUser.bSetPassword(rs.getString("user_password"));
                bUser.bSetName(rs.getString("user_name"));
                bUser.bSetSurname(rs.getString("user_surname"));
                bUser.bSetEmail(rs.getString("user_email"));
                bUser.bSetUserRole(UserRole.getUserRole(rs.getInt("user_role_id")));
                bUser.bSetActive(rs.getBoolean("is_active"));
            }
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return null;
        }
        return bUser.getResult();
    }

    /**
     * Find users by login in the database
     * Return list of all user which have a certain role
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param userRole
     * @return list of user
     */
    public List<User> findUserByRole(Connection connection, UserRole userRole) {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.UserQuery.SQL_SELECT_USER_BY_ROLE)){
            prst.setInt(1, userRole.getIndex());
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                User.Builder bUser = new User.Builder();
                bUser.bSetUserId(rs.getInt("user_id"));
                bUser.bSetLogin(rs.getString("login"));
                bUser.bSetPassword(rs.getString("user_password"));
                bUser.bSetName(rs.getString("user_name"));
                bUser.bSetSurname(rs.getString("user_surname"));
                bUser.bSetEmail(rs.getString("user_email"));
                bUser.bSetUserRole(UserRole.getUserRole(rs.getInt("user_role_id")));
                bUser.bSetActive(rs.getBoolean("is_active"));
                userList.add(bUser.getResult());
            }
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return userList;
    }

}
