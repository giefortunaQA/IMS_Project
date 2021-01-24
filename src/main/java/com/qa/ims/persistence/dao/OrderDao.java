package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order create(Order order) {
	    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO orders (fk_cid) VALUES (?);")) {
            statement.setLong(1, order.getFkCid());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
        	LOGGER.debug(e);
        	LOGGER.error(e.getMessage());
        }
        return null;
	}
	
    public Order readLatest() {
        try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY oid DESC LIMIT 1;")) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
