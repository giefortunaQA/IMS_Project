package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DatabaseUtilities;

public class CustomerDao implements IDomainDao<Customer> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Customer create(Customer customer) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO customers(first_name, surname,house_number,postcode) VALUES (?,?,?,?)")) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getSurname());
            statement.setLong(3, customer.getHouseNumber());
            statement.setString(4, customer.getPostCode());
            statement.executeUpdate();
            return readLatest();
        } 
        catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Customer read(Long cid) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE cid = ?")) {
            statement.setLong(1, cid);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> readAll() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(modelFromResultSet(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Customer readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY cid DESC LIMIT 1")) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE customers SET first_name = ?, surname = ?, house_number=?, postcode=? WHERE cid = ?")) {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getSurname());
            statement.setLong(3, customer.getHouseNumber());
            statement.setNString(4, customer.getPostCode());
            statement.setLong(5, customer.getCid());
            statement.executeUpdate();
            return read(customer.getCid());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
		return null;
    }

    @Override
    public int delete(long cid) {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            return statement.executeUpdate("delete from customers where cid = " + cid);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Customer modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long cid = resultSet.getLong("cid");
        String firstName = resultSet.getString("first_name");
        String surname = resultSet.getString("surname");
        Long houseNumber=resultSet.getLong("house_number");
        String postCode=resultSet.getNString("postcode");
        return new Customer(cid, firstName, surname,houseNumber,postCode);
    }

}
