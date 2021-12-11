package com.fedynets.dao;

import com.fedynets.constants.TourStatus;
import com.fedynets.constants.TourType;
import com.fedynets.entity.Order;
import com.fedynets.entity.Tour;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Class implements DAO interface and
 *  implements all the methods needed to work with the database
 *  Use singelton pattern
 */
public class OrderDao implements Dao<Order>{
    static final Logger LOG = LogManager.getLogger(TourDao.class);

    public OrderDao(){
    }

    /**
     * Add tour to the table ordering in database
     * Return true if the add operation was successful and false if SQLException occurred
     * @param connection
     * @param order
     * @return boolean
     */
    @Override
    public boolean add(Connection connection, Order order) {
        int newId = 0;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.OrderQuery.SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            prst.setInt(1, order.getUserId());
            prst.setInt(2, order.getTourId());
            prst.setDouble(3, order.getPrice());
            prst.setInt(4, order.getStepDisCount());
            prst.setInt(5, order.getDisCount());
            prst.setInt(6, order.getTourStatus().getIndex());
            prst.setString(7, order.getDescription());
            int oId = prst.executeUpdate();
            if(oId == 1){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        newId = generatedKeys.getInt(1);
                        order.setOrderId(newId);
                    }
                }
            }
        }catch(SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Return list of all order when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @return list of hotel
     */
    @Override
    public List<Order> findAll(Connection connection) {
        List<Order> orderList = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery.OrderQuery.SQL_SELECT_ALL)) {
            while (rs.next()) {
                Order.Builder bOrder = new Order.Builder();
                bOrder.bSetOrderId(rs.getInt("ordering_id"));
                bOrder.bSetUserId(rs.getInt("user_id"));
                bOrder.bSetTourId(rs.getInt("tour_id"));
                bOrder.bSetPrice(rs.getDouble("price"));
                bOrder.bSetStepDisCount(rs.getInt("step_discount"));
                bOrder.bSetDisCount(rs.getInt("discount"));
                bOrder.bSetTourStatus(TourStatus.getTourStatus(rs.getInt("tour_staus_id")));
                bOrder.bSetDescription(rs.getString("ordering_description"));
                orderList.add(bOrder.getResult());
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return orderList;
    }
    /**
     * Delete tour by number in the database
     * Return true if the delete operation was successful and false if SQLException occurred
     * @param connection
     * @param id
     * @return boolean
     */
    public boolean delete(Connection connection, int id)  {
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.OrderQuery.SQL_DELETE_ORDER)){
            prst.setInt(1, id);
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
     * Update tour by id in the database
     * Return true if the update operation was successful and false if SQLException occurred
     * @param connection
     * @param order
     * @return boolean
     */
    @Override
    public boolean update(Connection connection, Order order) {
        boolean result;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.OrderQuery.SQL_UPDATE_ORDER_BY_ID)){
            prst.setInt(1, order.getUserId());
            prst.setInt(2, order.getTourId());
            prst.setDouble(3, order.getPrice());
            prst.setInt(4, order.getStepDisCount());
            prst.setInt(5, order.getDisCount());
            prst.setInt(6, order.getTourStatus().getIndex());
            prst.setString(7, order.getDescription());
            prst.setInt(8, order.getOrderId());
            result = prst.executeUpdate() > 0;
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return result;
    }

    /**
     * Find orders by tour status in the database
     * Return list of all order which have a certain status
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param tourStatus
     * @return list of tour
     */
    public List<Order> findOrderByTourStatus(Connection connection, TourStatus tourStatus){
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.OrderQuery.SQL_SELECT_ORDER_BY_TOUR_STATUS)) {
            prst.setInt(1, tourStatus.getIndex());
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                Order.Builder bOrder = new Order.Builder();
                bOrder.bSetOrderId(rs.getInt("ordering_id"));
                bOrder.bSetUserId(rs.getInt("user_id"));
                bOrder.bSetTourId(rs.getInt("tour_id"));
                bOrder.bSetPrice(rs.getDouble("price"));
                bOrder.bSetStepDisCount(rs.getInt("step_discount"));
                bOrder.bSetDisCount(rs.getInt("discount"));
                bOrder.bSetTourStatus(TourStatus.getTourStatus(rs.getInt("tour_staus_id")));
                bOrder.bSetDescription(rs.getString("ordering_description"));
                orderList.add(bOrder.getResult());
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return orderList;
    }

    /**
     * Find order by id in the database
     * Return order when there are not exception
     * and null when happen SQLException
     * @param connection
     * @param id
     * @return user
     */
    public Order findOrderByOrderId(Connection connection, int id){
        Order.Builder bOrder = new Order.Builder();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.OrderQuery.SQL_SELEECT_ODER_BY_ID)) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            if(rs.next()){
                bOrder.bSetOrderId(rs.getInt("ordering_id"));
                bOrder.bSetUserId(rs.getInt("user_id"));
                bOrder.bSetTourId(rs.getInt("tour_id"));
                bOrder.bSetPrice(rs.getDouble("price"));
                bOrder.bSetStepDisCount(rs.getInt("step_discount"));
                bOrder.bSetDisCount(rs.getInt("discount"));
                bOrder.bSetTourStatus(TourStatus.getTourStatus(rs.getInt("tour_staus_id")));
                bOrder.bSetDescription(rs.getString("ordering_description"));
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return null;
        }
        return bOrder.getResult();
    }
}
