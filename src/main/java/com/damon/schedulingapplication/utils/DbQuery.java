package com.damon.schedulingapplication.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DbQuery class to handle prepared statements
 * @author Damon Vessey
 */
public class DbQuery {

    private static PreparedStatement preparedStatement; //prepared statement reference

    /**
     * getPreparedStatement to get prepared statement
     * @return
     */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * setPreparedStatement to set prepared statement
     * @param conn
     * @param sqlStatement
     * @throws SQLException
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }
}
