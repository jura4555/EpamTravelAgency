package com.fedynets.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * DBManager. Works as Pool Connection
 * Use DataSource object
 * @autor Yurii Fedynets
 */
public class DBManager {
    static final Logger LOG = (Logger) LogManager.getLogger(DBManager.class);

    static DBManager instance;
    static DataSource dataSource;

    /**
     * Use pattern singelton
     * @return object DBManager
     */
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /*
    public static Connection getConnection(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/app.properties"));
        } catch (IOException e) {
            LOG.error("Problem with load file " + e.fillInStackTrace());
        }
        String url = properties.getProperty("connection.url");
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            LOG.error("Problem with DriverManager " + throwables.fillInStackTrace());
        }
        return con;
    }
*/

    /**
     * Constructor
     * Setting object DataSource
     * Before using this construstor you must configure the Date Source
     * and the Connections Pool in your WEB_APP_ROOT/META-INF/context.xml file.
     */
    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(
                    "java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/ConnectPool");
        } catch (Exception e) {
            LOG.error("problem with dataSource: " + e.fillInStackTrace());
        }
    }


    /**
     * Allow use connection
     * @return DB Connection
     * @throws SQLException
     */

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Cannot obtain a data source", e);
            throw new IllegalStateException("Cannot obtain a data source", e);
        }
        return connection;
    }


    /**
     * Commit and close the given connection.
     * @param connection
     */
    public void commitAndClose(Connection connection){
        try {
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            LOG.error(throwables.getMessage());
        }
    }

    /**
     * Rollback and close the given connection.
     * @param connection
     */
    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * Close PreparedStatement, Statement and ResultSet
     * @param autoCloseable
     */
    public void close(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }


}
