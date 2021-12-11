package com.fedynets.dao;

import com.fedynets.constants.HotelType;
import com.fedynets.constants.TourType;
import com.fedynets.entity.Hotel;
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
public class HotelDao implements Dao<Hotel>{
    static final Logger LOG = LogManager.getLogger(HotelDao.class);

    public HotelDao(){
    }

    /**
     * Add hotel to the table hotel in database
     * Return true if the add operation was successful and false if SQLException occurred
     * @param connection
     * @param hotel
     * @return boolean
     */
    @Override
    public boolean add(Connection connection, Hotel hotel) {
        int newId = 0;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.HotelQuery.SQL_ADD_HOTEL, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, hotel.getName());
            prst.setInt(2, hotel.getHotelType().getIndex());
            int hId = prst.executeUpdate();
            if(hId == 1){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        newId = generatedKeys.getInt(1);
                        hotel.setHotelId(newId);
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
     * Return list of all hotel when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @return list of hotel
     */
    @Override
    public List<Hotel> findAll(Connection connection) {
        List<Hotel> hotelList = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery.HotelQuery.SQL_SELECT_ALL)){
            while(rs.next()){
                Hotel.Builder bHotel = new Hotel.Builder();
                bHotel.bSetHotelId(rs.getInt("hotel_id"));
                bHotel.bSetName(rs.getString("hotel_name"));
                bHotel.bSetHotelType(HotelType.getHotelType(rs.getInt("hotel_type_id")));
                hotelList.add(bHotel.getResults());
            }
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return hotelList;
    }

    /**
     * Delete hotel by name in the database
     * Return true if the delete operation was successful and false if SQLException occurred
     * @param connection
     * @param name
     * @return boolean
     */
    public boolean delete(Connection connection, String name) {
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.HotelQuery.SQL_DELETE_HOTEL)){
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
     * Update hotel by name in the database
     * Return true if the update operation was successful and false if SQLException occurred
     * @param connection
     * @param hotel
     * @return boolean
     */
    @Override
    public boolean update(Connection connection, Hotel hotel) {
        boolean result;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.HotelQuery.SQL_UPDATE_HOTEL_BY_NAME)){
            prst.setInt(1, hotel.getHotelType().getIndex());
            prst.setString(2, hotel.getName());
            result = prst.executeUpdate() > 0;
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return result;
    }

}
