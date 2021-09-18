package com.damon.schedulingapplication.DAO;

import com.damon.schedulingapplication.Controller.LoginController;
import com.damon.schedulingapplication.Model.*;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * CustomerDaoImpl class to handle all Customer database transactions
 * @author Damon Vessey
 */
public class CustomerDaoImpl {
    /**
     * getCountries method to retrieve all countries from database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<Countries> getCountries() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM countries";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int countryID = resultSet.getInt("Country_ID");
            String countryName = resultSet.getString("Country");

            countriesObservableList.add(new Countries(countryID, countryName));
        }
        DbConnection.closeConnection();
        return countriesObservableList;
    }

    /**
     * getFirstLevelDivisions method to retrieve first level division based on selected country id
     * @param selectedCountryId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivisions> getFirstLevelDivisions(int selectedCountryId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM first_level_divisions WHERE first_level_divisions.Country_ID = '" + selectedCountryId + "'";
        //String selectStatement = "SELECT * FROM first_level_divisions";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<FirstLevelDivisions> divisionsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int divisionID = resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            int countryID = resultSet.getInt("Country_ID");

            divisionsObservableList.add(new FirstLevelDivisions(divisionID, division, countryID));
        }
        DbConnection.closeConnection();
        return divisionsObservableList;
    }

    public static FirstLevelDivisions getFirstLevelDivision(int divisionId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM first_level_divisions WHERE Division_ID = '" + divisionId + "'";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int divisionID = resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            int countryID = resultSet.getInt("Country_ID");

            FirstLevelDivisions firstLevelDivisions = new FirstLevelDivisions(divisionID, division, countryID);
            DbConnection.closeConnection();
            return firstLevelDivisions;
        }
        return null;
    }

    /**
     * getCustomersCountry method to retrieve customers by country
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<Customers> getCustomersCountry() throws ClassNotFoundException, SQLException {
     Connection conn = DbConnection.startConnection();
     String selectStatement =  "SELECT * FROM customers, first_level_divisions, countries "
              + "WHERE customers.Division_ID = first_level_divisions.Division_ID "
              + "AND first_level_divisions.COUNTRY_ID = countries.Country_ID "
              + "ORDER BY Customer_ID";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Customers> customers = FXCollections.observableArrayList();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String createdBy = resultSet.getString("Created_By");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            String division = resultSet.getString("Division");
            String countryName = resultSet.getString("Country");
            int countryId = resultSet.getInt("Country_ID");
            int divisionId = resultSet.getInt("Division_ID");

            customers.add(new Customers(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, division, countryName, countryId, divisionId));
        }
        DbConnection.closeConnection();
        return customers;
    }

    /**
     * addCustomer method to save customer to database
     * @param customer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void addCustomer(Customers customer) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DbQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

        String customerName = customer.getCustomer_Name();
        String address = customer.getAddress();
        String postalCode = customer.getPostal_Code();
        String phone = customer.getPhone();
        LocalDateTime createDate = customer.getCreate_Date();
        String createdBy = customer.getCreated_By();
        Timestamp lastUpdate = customer.getLast_Update();
        String lastUpdatedBy = customer.getLast_Updated_By();
        int divisionID = customer.getDivision_ID();

        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setObject(5, createDate);
        preparedStatement.setString(6, createdBy);
        preparedStatement.setObject(7, lastUpdate);
        preparedStatement.setString(8, lastUpdatedBy);
        preparedStatement.setInt(9, divisionID);

        preparedStatement.execute();

        DbConnection.closeConnection();
    }

    /**
     * getCustomers method to retrieve all customers from database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<Customers> getCustomers() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM customers";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Customers> customers = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String createdBy = resultSet.getString("Created_By");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int divisionId = resultSet.getInt("Division_ID");

            customers.add(new Customers(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId));
        }
        DbConnection.closeConnection();
        return customers;

    }

    /**
     * getCustomer method to retrieve customer by customer Id
     * @param customerId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Customers getCustomer(int customerId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM customers WHERE Customer_ID = '" + customerId + "'";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()){
            int theCustomerId = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");

            Customers theCustomer = new Customers(theCustomerId, customerName);
            DbConnection.closeConnection();
            return theCustomer;
        }
        return null;
    }

    /**
     * deleteCustomer method to delete customer
     * @param customerId
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void deleteCustomer(int customerId) throws ClassNotFoundException, SQLException {
        deleteCustomerAppointments(customerId);
        Connection conn = DbConnection.startConnection();
        String deleteStatement = "DELETE FROM customers WHERE Customer_ID = " + customerId;

        DbQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.executeUpdate();
        DbConnection.closeConnection();
    }

    /**
     * deleteCustomerAppointments method to delete all appointments associated with customer Id
     * @param customerId
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void deleteCustomerAppointments(int customerId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Customer_ID = " + customerId;

        DbQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.executeUpdate();
        DbConnection.closeConnection();
    }

    /**
     * updateCustomer method to update customer in database
     * @param customer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void updateCustomer(Customers customer) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String updateStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = " + customer.getCustomer_ID();
        DbQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

        Users theUser = LoginController.getTheUser();
        String userName = theUser.getUser_name();

        preparedStatement.setString(1, customer.getCustomer_Name());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPostal_Code());
        preparedStatement.setString(4, customer.getPhone());
        preparedStatement.setObject(5, customer.getCreate_Date());
        preparedStatement.setString(6, customer.getCreated_By());
        preparedStatement.setObject(7, Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setString(8, userName);
        preparedStatement.setInt(9, customer.getDivision_ID());

        preparedStatement.executeUpdate();
        DbConnection.closeConnection();
    }

    /**
     * getCountry method to get country by id
     * @param countryId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Countries getCountry(int countryId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Country FROM countries where Country_ID = " + countryId;
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            String CountryName = resultSet.getString("Country");

            countriesObservableList.add(new Countries(countryId, CountryName));
        }
        DbConnection.closeConnection();
        return countriesObservableList.get(0);
    }
}
