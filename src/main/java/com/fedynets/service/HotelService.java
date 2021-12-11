package com.fedynets.service;

import com.fedynets.dao.HotelDao;
import com.fedynets.entity.Hotel;
import com.fedynets.entity.User;
import com.fedynets.util.DBManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HotelService {
    static final Logger LOG = LogManager.getLogger(HotelService.class);

    public HotelService(){
    }

    public boolean addHotel(Hotel hotel) {
        Connection connection = DBManager.getInstance().getConnection();
        HotelDao hotelDao = new HotelDao();
        hotelDao.add(connection, hotel);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public List<Hotel> findAllHotel() {
        Connection connection = DBManager.getInstance().getConnection();
        HotelDao hotelDao = new HotelDao();
        List<Hotel> hotelList = hotelDao.findAll(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return hotelList;
    }

    public boolean deleteHoltel(String name){
        Connection connection = DBManager.getInstance().getConnection();
        HotelDao hotelDao = new HotelDao();
        hotelDao.delete(connection, name);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }

    public boolean updateHotel(Hotel hotel) {
        Connection connection = DBManager.getInstance().getConnection();
        HotelDao hotelDao = new HotelDao();
        hotelDao.update(connection, hotel);
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.fillInStackTrace());
        }
        return true;
    }


}
