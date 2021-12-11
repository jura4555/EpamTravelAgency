package com.fedynets.service;

import com.fedynets.constants.HotelType;
import com.fedynets.constants.TourType;
import com.fedynets.dao.TourDao;
import com.fedynets.entity.Tour;
import com.fedynets.entity.User;
import com.fedynets.util.DBManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TourService {
    static final Logger LOG = LogManager.getLogger(TourService.class);

    public TourService(){
    }

    public boolean addTour(Tour tour) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        tourDao.add(connection, tour);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public List<Tour> findAllTour() {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        List<Tour> tourList = tourDao.findAll(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tourList;
    }

    public boolean deleteTour(String name)  {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        tourDao.delete(connection, name);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public boolean updateTour(Tour tour) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        tourDao.update(connection, tour);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public Tour findTourByName(String name) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        Tour tour = tourDao.findTourByName(connection, name);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tour;
    }

    public List<Tour> findTourByTourType(TourType tourType) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        List<Tour> tourList = tourDao.findTourByTourType(connection, tourType);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tourList;
    }

    public List<Tour> findTourByPlaceCount(int count) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        List<Tour> tourList = tourDao.findTourByPlaceCount(connection, count);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tourList;
    }

    public List<Tour> findTourByPrice(int minPrice, int maxPrice) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        List<Tour> tourList = tourDao.findTourByPrice(connection, minPrice, maxPrice);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tourList;
    }

    public List<Tour> findTourByHotelType(HotelType hotelType) {
        Connection connection = DBManager.getInstance().getConnection();
        TourDao tourDao = new TourDao();
        List<Tour> tourList = tourDao.findTourByHotelType(connection, hotelType);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return tourList;
    }

}
