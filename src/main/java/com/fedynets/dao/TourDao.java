package com.fedynets.dao;

import com.fedynets.constants.HotelType;
import com.fedynets.constants.TourType;
import com.fedynets.entity.Tour;
import com.fedynets.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Class implements DAO interface and
 *  implements all the methods needed to work with the database
 *  Use singelton pattern
 */
public class TourDao implements Dao<Tour>{
    static final Logger LOG = LogManager.getLogger(TourDao.class);

    public TourDao(){
    }

    /**
     * Add tour to the table hotel in database
     * Return true if the add operation was successful and false if SQLException occurred
     * @param connection
     * @param tour
     * @return boolean
     */
    @Override
    public boolean add(Connection connection, Tour tour) {
        int newId = 0;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_ADD_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, tour.getName());
            prst.setDouble(2, tour.getPrice());
            prst.setObject(3, tour.getDateDaparture());
            prst.setObject(4, tour.getDateArrival());
            prst.setInt(5, tour.getMaxDisCount());
            prst.setInt(6, tour.getPlaceCount());
            prst.setInt(7, tour.getHotelId());
            prst.setInt(8, tour.getTourType().getIndex());
            prst.setBoolean(9, tour.isDurning());
            int tId = prst.executeUpdate();
            if(tId == 1){
                try(ResultSet generatedKeys = prst.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        newId = generatedKeys.getInt(1);
                        tour.setTourId(newId);
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
     * Return list of all tour when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @return list of hotel
     */
    @Override
    public List<Tour> findAll(Connection connection) {
        List<Tour> tourList = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQLQuery.TourQuery.SQL_SELECT_ALL)){
            while(rs.next()){
                Tour.Builder bTour = new Tour.Builder();
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
                tourList.add(bTour.getResult());
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return tourList;
    }

    /**
     * Delete tour by name in the database
     * Return true if the delete operation was successful and false if SQLException occurred
     * @param connection
     * @param name
     * @return boolean
     */
    public boolean delete(Connection connection, String name)  {
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_DELETE_TOUR)){
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
     * Update tour by name in the database
     * Return true if the update operation was successful and false if SQLException occurred
     * @param connection
     * @param tour
     * @return boolean
     */
    @Override
    public boolean update(Connection connection, Tour tour) {
        boolean result;
        try (PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_UPDATE_TOUR_BY_NAME)){
            prst.setDouble(1, tour.getPrice());
            prst.setObject(2, tour.getDateDaparture());
            prst.setObject(3, tour.getDateArrival());
            prst.setInt(4, tour.getMaxDisCount());
            prst.setInt(5, tour.getPlaceCount());
            prst.setInt(6, tour.getHotelId());
            prst.setInt(7, tour.getTourType().getIndex());
            prst.setBoolean(8, tour.isDurning());
            prst.setString(9, tour.getName());
            result = prst.executeUpdate() > 0;
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return false;
        }
        return result;
    }

    /**
     * Find tour by name in the database
     * Return tour when there are not exception
     * and null when happen SQLException
     * @param connection
     * @param name
     * @return user
     */
    public Tour findTourByName(Connection connection, String name) {
        Tour.Builder bTour = new Tour.Builder();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_SELECT_TOUR_BY_TOUR_NAME)){
            prst.setString(1, name);
            ResultSet rs = prst.executeQuery();
            while(rs.next()){
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return null;
        }
        return bTour.getResult();
    }

    /**
     * Find tours by name in the database
     * Return list of all tour which have a certain type
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param tourType
     * @return list of tour
     */
    public List<Tour> findTourByTourType(Connection connection, TourType tourType) {
        List<Tour> tourList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_SELECT_TOUR_BY_TOUR_TYPE)) {
            prst.setInt(1, tourType.getIndex());
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Tour.Builder bTour = new Tour.Builder();
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
                tourList.add(bTour.getResult());
                }
            } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return tourList;
    }

    /**
     * Find tours by count in the database
     * Return list of all tour which have a certain place count
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param count
     * @return list of tour
     */
    public List<Tour> findTourByPlaceCount(Connection connection, int count) {
        List<Tour> tourList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_SELECT_TOUR_BY_TOUR_COUNT)) {
            prst.setInt(1, count);
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Tour.Builder bTour = new Tour.Builder();
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
                tourList.add(bTour.getResult());
            }
        } catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return tourList;
    }

    /**
     * Find tours by price in the database
     * Return list of all tour which are in the range between min and max price
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param minPrice
     * @param maxPrice
     * @return list of tour
     */
    public List<Tour> findTourByPrice(Connection connection, int minPrice, int maxPrice) {
        List<Tour> tourList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_SELECT_TOUR_BY_PRICE)) {
            prst.setInt(1, minPrice);
            prst.setInt(2, maxPrice);
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Tour.Builder bTour = new Tour.Builder();
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
                tourList.add(bTour.getResult());
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return tourList;
    }

    /**
     * Find tours by hotel type of tour in the database
     * Return list of all tour which have certain type hotel
     * when there are not exception
     * and empty list when happen SQLException
     * @param connection
     * @param hotelType
     * @return list of tour
     */
    public List<Tour> findTourByHotelType(Connection connection, HotelType hotelType) {
        List<Tour> tourList = new ArrayList<>();
        try(PreparedStatement prst = connection.prepareStatement(SQLQuery.TourQuery.SQL_SELECT_TOUR_BY_HOTEL_TYPE)) {
            prst.setInt(1, hotelType.getIndex());
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Tour.Builder bTour = new Tour.Builder();
                bTour.bSetTourId(rs.getInt("tour_id"));
                bTour.bSetName(rs.getString("tour_name"));
                bTour.bSetPrice(rs.getDouble("tour_price"));
                bTour.bSetDateDaparture(rs.getDate("date_departure").toLocalDate());
                bTour.bSetDateArrival(rs.getDate("date_arrival").toLocalDate());
                bTour.bSetMaxDisCount(rs.getInt("max_discount"));
                bTour.bSetPlaceCount(rs.getInt("place_count"));
                bTour.bSetHotelId(rs.getInt("hotel_id"));
                bTour.bSetTourType(TourType.getTourType(rs.getInt("tour_type_id")));
                bTour.bSetDurning(rs.getBoolean("is_burning"));
                tourList.add(bTour.getResult());
            }
        }catch (SQLException e){
            LOG.error("problem " + e.getMessage());
            return Collections.emptyList();
        }
        return tourList;
    }

}
