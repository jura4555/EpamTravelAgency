package com.fedynets.dao;

/**
 * Class for saving SQL Query
 * which use in Dao classes
 * @author Yurii Fedynets
 */
public class SQLQuery {
    static class UserQuery {
        public static final String SQL_ADD_USER = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
        public static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET login = ?, user_password = ?, user_name = ?, " +
                "user_surname = ?, user_email = ?, user_role_id = ?, is_active = ?  WHERE user_id = ?;";
        public static final String SQL_DELETE_USER = "DELETE FROM user WHERE (login = ?);";
        public static final String SQL_SELECT_ALL = "SELECT * FROM user ORDER BY login;";
        public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?;";
        public static final String SQL_SELECT_USER_BY_ROLE = "SELECT * FROM user WHERE user_role_id = ? ORDER BY login;";
    }

    static class HotelQuery{
        public static final String SQL_ADD_HOTEL = "INSERT INTO hotel VALUES (DEFAULT, ?, ?);";
        public static final String SQL_SELECT_ALL = "SELECT * FROM hotel ORDER BY hotel_name;";
        public static final String SQL_DELETE_HOTEL = "DELETE FROM hotel WHERE (hotel_name = ?);";
        public static final String SQL_UPDATE_HOTEL_BY_NAME = "UPDATE hotel SET hotel_type_id = ? WHERE hotel_name = ?;";
        //public static final String SQL_SELECT_HOTEL_BY_ID = "SELECT * FROM hotel WHERE hotel_id = ?;";

    }

    static class TourQuery{
        public static final String SQL_ADD_TOUR = "INSERT INTO tour VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        public static final String SQL_SELECT_ALL = "SELECT * FROM tour ORDER BY is_burning DESC, tour_name";
        public static final String SQL_DELETE_TOUR = "DELETE FROM tour WHERE (tour_name = ?);";
        public static final String SQL_UPDATE_TOUR_BY_NAME = "UPDATE tour SET tour_price = ?, date_departure = ?, " +
                "date_arrival = ?, max_discount = ?, place_count = ?, hotel_id = ?, tour_type_id = ?, is_burning = ? " +
                "WHERE tour_name = ?;";
        public static final String SQL_SELECT_TOUR_BY_TOUR_NAME = "SELECT * FROM tour WHERE tour_name = ?;";
        public static final String SQL_SELECT_TOUR_BY_TOUR_TYPE = "SELECT * FROM tour WHERE tour_type_id = ? ORDER BY is_burning DESC, tour_name;";
        public static final String SQL_SELECT_TOUR_BY_TOUR_COUNT = "SELECT * FROM tour WHERE place_count = ? ORDER BY is_burning DESC, tour_name;";
        public static final String SQL_SELECT_TOUR_BY_PRICE = "SELECT * FROM tour WHERE tour_price > ? AND tour_price < ? ORDER BY is_burning DESC, tour_name;";
        public static final String SQL_SELECT_TOUR_BY_HOTEL_TYPE = "SELECT " +
                "tour_id, tour_name, tour_price, date_departure, date_arrival, " +
                "max_discount, place_count, tour.hotel_id, tour_type_id, is_burning FROM db_agency.tour " +
                "INNER JOIN db_agency.hotel ON tour.hotel_id = hotel.hotel_id WHERE hotel.hotel_type_id = ? " +
                "ORDER BY is_burning DESC, tour_name;";
    }

    static class OrderQuery{
        public static final String SQL_ADD_ORDER = "INSERT INTO ordering VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
        public static final String SQL_SELECT_ALL = "SELECT * FROM ordering;";
        public static final String SQL_DELETE_ORDER = "DELETE FROM ordering WHERE (ordering_id = ?);";
        public static final String SQL_UPDATE_ORDER_BY_ID = "UPDATE ordering SET user_id = ?, tour_id = ?, price = ?, " +
                "step_discount = ?, discount = ?, tour_staus_id = ?, ordering_description = ? WHERE ordering_id = ?;";
        public static final String SQL_SELECT_ORDER_BY_TOUR_STATUS = "SELECT * FROM ordering WHERE tour_staus_id = ?;";
        public static final String SQL_SELEECT_ODER_BY_ID = "SELECT * FROM ordering WHERE ordering_id = ?;";
    }
}
