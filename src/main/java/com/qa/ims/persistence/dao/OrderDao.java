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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;

public class OrderDao implements IDomainDao<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order create(Order order) {
	    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO orders (fk_cid) VALUES (?);")){
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
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders;")) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
	}
	public List<Item> readItems(Order order) {
	    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT oi.fk_iid,i.name,i.price FROM orders_items oi JOIN items i ON i.iid=oi.fk_iid WHERE oi.fk_oid=?;")){
	    	List<Item> items= new ArrayList<>();
            statement.setLong(1, order.getOid());
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
            	Long iid= resultSet.getLong("fk_iid");
            	String name= resultSet.getString("name");
            	Double price=resultSet.getDouble("price");
            	items.add(new Item(iid,name,price));
            }
            return items;
        } catch (Exception e) {
        	LOGGER.debug(e);
        	LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
	}
	public Order read(Long oid) {
	    try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	               PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE oid = ?");) {
	           statement.setLong(1, oid);
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
	public Order update(Order order) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE orders SET fk_cid=? WHERE oid = ?;")) {
            statement.setLong(1, order.getFkCid());
            statement.setLong(2, order.getOid());
            statement.executeUpdate();
            return read(order.getOid());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
		return null;
	}
	
	public void updateAdd(Order order) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO orders_items (fk_oid,fk_iid) VALUES (?,?);")) {
            statement.setLong(1, order.getOid());
            statement.setDouble(2, order.getIid());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
	}
	
	public void updateDelete(Order order) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE from orders_items WHERE oid= ? AND iid=? LIMIT 1")) {
            statement.setLong(1, order.getOid());
            statement.setDouble(2, order.getIid());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
	}
	

	

	@Override
	public int delete(long oid) {
		 try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();) {
	            return statement.executeUpdate("delete from orders where oid = " + oid+";");
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long oid = resultSet.getLong("oid");
		Long fkCid= resultSet.getLong("fk_cid");
		Double orderValue = resultSet.getDouble("order_value");
		return new Order(oid,fkCid,orderValue);
	}
	
	public Order getValue(Order order) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT oi.fk_oid,SUM(i.price) AS total FROM orders_items oi JOIN items i ON i.iid=oi.fk_iid WHERE oi.fk_oid=?;")) {
            statement.setLong(1, order.getOid());
            ResultSet result=statement.executeQuery();
            while (result.next()) {
            	Long oid=result.getLong("fk_oid");
            	Double orderValue=result.getDouble("total");
            	return new Order(oid,null,orderValue);}
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
		return null;
	}
	
	public void setValue(Order order) {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE orders SET order_value=? where oid=?;")) {
            statement.setDouble(1, order.getOrderValue());
            statement.setLong(2, order.getOid());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
	}

}
