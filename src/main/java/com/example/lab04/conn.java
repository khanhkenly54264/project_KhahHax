package com.example.lab04;

import com.example.lab04.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class conn {
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customers (CusUser, CusPass, CusName, CusPhone, CusAdd, CusEmail, CusFacebook, CusSkyper, CusStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customers";
    private static final String SELECT_CUSTOMER_BY_CUSUSER = "SELECT * FROM Customers WHERE CusUser = ?";
    private static final String SELECT_CUSTOMER_BY_CUSID = "SELECT * FROM Customers WHERE CuID = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE Customers SET CusPass=?, CusName=?, CusPhone=?, CusAdd=?, CusEmail=?, CusFacebook=?, CusSkyper=?, CusStatus=? WHERE CusID=?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM Customers WHERE CuID=?";
    public static Connection getMSSQLConnection() throws SQLException, ClassNotFoundException{
        String URL = "jdbc:sqlserver://ADMIN-PC:1433;databaseName=Customers;encrypt=false;";
        String user="sa";
        String password="12345a.";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(URL,user, password);
        return conn;
    }
    public void insertCustomer(Customer customer) throws SQLException,ClassNotFoundException {
        try (Connection connection = getMSSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getCusUser());
            preparedStatement.setString(2, customer.getCusPass());
            preparedStatement.setString(3, customer.getCusName());
            preparedStatement.setString(4, customer.getCusPhone());
            preparedStatement.setString(5, customer.getCusAdd());
            preparedStatement.setString(6, customer.getCusEmail());
            preparedStatement.setString(7, customer.getCusFacebook());
            preparedStatement.setString(8, customer.getCusSkyper());
            preparedStatement.setInt(9, customer.getCusStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> selectAllCustomers() throws SQLException,ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getMSSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int cusID = rs.getInt("CuID");
                String cusUser = rs.getString("CusUser");
                String cusPass = rs.getString("CusPass");
                String cusName = rs.getString("CusName");
                String cusPhone = rs.getString("CusPhone");
                String cusAdd = rs.getString("CusAdd");
                String cusEmail = rs.getString("CusEmail");
                String cusFacebook = rs.getString("CusFacebook");
                String cusSkyper = rs.getString("CusSkyper");
                int cusStatus = rs.getInt("CusStatus");

                customers.add(new Customer(cusID, cusUser, cusPass, cusName, cusPhone, cusAdd, cusEmail, cusFacebook, cusSkyper, cusStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public  Customer selectCustomerByCusID(int cusID) throws SQLException,ClassNotFoundException {
        Customer customer = null;
        try (Connection connection = getMSSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_CUSID)) {
            preparedStatement.setInt(1, cusID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String cusUser = rs.getString("cusUser");
                String cusPass = rs.getString("CusPass");
                String cusName = rs.getString("CusName");
                String cusPhone = rs.getString("CusPhone");
                String cusAdd = rs.getString("CusAdd");
                String cusEmail = rs.getString("CusEmail");
                String cusFacebook = rs.getString("CusFacebook");
                String cusSkyper = rs.getString("CusSkyper");
                int cusStatus = rs.getInt("CusStatus");

                customer = new Customer(cusID, cusUser, cusPass, cusName, cusPhone, cusAdd, cusEmail, cusFacebook, cusSkyper, cusStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public List <Customer> selectCustomerByCusUser(String cusUser) throws SQLException,ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = getMSSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_CUSUSER)) {
            preparedStatement.setString(1, cusUser);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cuID = rs.getInt("CuID");
                String cusPass = rs.getString("CusPass");
                String cusName = rs.getString("CusName");
                String cusPhone = rs.getString("CusPhone");
                String cusAdd = rs.getString("CusAdd");
                String cusEmail = rs.getString("CusEmail");
                String cusFacebook = rs.getString("CusFacebook");
                String cusSkyper = rs.getString("CusSkyper");
                int cusStatus = rs.getInt("CusStatus");
                customers.add( new Customer(cuID, cusUser, cusPass, cusName, cusPhone, cusAdd, cusEmail, cusFacebook, cusSkyper, cusStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean updateCustomer(Customer customer) throws SQLException,ClassNotFoundException {
        boolean rowUpdated;
        try (Connection connection = getMSSQLConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            statement.setString(1, customer.getCusPass());
            statement.setString(2, customer.getCusName());
            statement.setString(3, customer.getCusPhone());
            statement.setString(4, customer.getCusAdd());
            statement.setString(5, customer.getCusEmail());
            statement.setString(6, customer.getCusFacebook());
            statement.setString(7, customer.getCusSkyper());
            statement.setInt(8, customer.getCusStatus());
            statement.setInt(9, customer.getCusID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteCustomer(int cusID) throws SQLException,ClassNotFoundException {
        boolean rowDeleted;
        try (Connection connection = getMSSQLConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            statement.setInt(1, cusID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        System.out.println("get connections ....");
        Connection conn = getMSSQLConnection();
        System.out.println("get connections "+conn);
        System.out.println("Done");
    }

}
