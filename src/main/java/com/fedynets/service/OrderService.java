package com.fedynets.service;

import com.fedynets.constants.TourStatus;
import com.fedynets.dao.OrderDao;
import com.fedynets.dao.TourDao;
import com.fedynets.entity.Order;
import com.fedynets.entity.Tour;
import com.fedynets.util.DBManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {

    static final Logger LOG = LogManager.getLogger(OrderService.class);

    public OrderService(){
    }

    public boolean addOrder(Order order) {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        orderDao.add(connection, order);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public List<Order> findAllOrder() {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        List<Order> orderList = orderDao.findAll(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return orderList;
    }

    public boolean delete(int id)  {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        orderDao.delete(connection, id);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public boolean update(Order order) {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        orderDao.update(connection, order);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public List<Order> findOrderByTourStatus(TourStatus tourStatus) {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        List<Order> orderList = orderDao.findOrderByTourStatus(connection, tourStatus);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return orderList;
    }

    public Order findOrderByOrderId(int id) {
        Connection connection = DBManager.getInstance().getConnection();
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.findOrderByOrderId(connection, id);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return order;
    }

}
