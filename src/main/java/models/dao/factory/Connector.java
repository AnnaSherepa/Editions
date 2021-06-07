package models.dao.factory;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;



public class Connector {
    private static final String URL_PATH = "jdbc:mysql://localhost:3307/finalProject?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private static final Logger LOGGER = Logger.getLogger(Connector.class);
    private Connector() {}
    private static volatile Connector db;

    public static synchronized  Connector getInstance() {
        if (db == null) {
            db = new Connector();
        }
        return db;
    }


    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            LOGGER.info("Contex is getted");

            DataSource ds = (DataSource)envContext.lookup("jdbc/finalproject");

            LOGGER.info("DataSource is created");
            LOGGER.info(ds.toString());
            con = ds.getConnection();
            LOGGER.info("Connection is obtained");
        } catch (NamingException ex) {
            LOGGER.error("Cannot obtain a connection from the pool", ex);
        }
        return con;
    }

    /**
     * Commit and close the given connection
     *
     * @param con - connection to be commited and closed
     * */
    public void commitAndClose(final Connection con) {
        try {
            con.commit();
            con.close();
            System.out.println("\n\n******CONNECTION CLOSED!!******\n\n");
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }
    public void close(final AutoCloseable resource){
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con - connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
