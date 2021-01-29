package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;

public class OrderController implements ICrudController<Order>{
	
	public static final Logger LOGGER = LogManager.getLogger();
    private OrderDao orderDao;
    private JavaUtilities javaUtilities;
    
    public OrderController(OrderDao orderDao, JavaUtilities javaUtilities) {
        super();
        this.orderDao = orderDao;
        this.javaUtilities = javaUtilities;
    }
    
	@Override
	public Order create() {
		boolean keepAdd=true;
        List<Long> itemList= new ArrayList<>();
        LOGGER.info("Please enter customer id");
        Long fkCid = javaUtilities.getLong();
        
        while (keepAdd) {
        	LOGGER.info("Please enter item id to order\n >> '0' to stop adding");
            Long fkIid=javaUtilities.getLong();
            if (fkIid!=0) {
            	itemList.add(fkIid);
            	LOGGER.info("Added!");
            } else {
            	keepAdd=false;
            	}
        }
        Order order = orderDao.create(new Order(fkCid,0.0));
        for (Long iid: itemList) {
        	orderDao.updateAdd(new Order(order.getOid(),iid));
        }
        LOGGER.info("Order record created.");
        return order;
	}

	@Override
	public List<Order> readAll() {
		LOGGER.info("What would you like to do in READ"
				+ "\n -> (1) View all orders"
				+ "\n -> (2) View all items in an order");
		String choice=javaUtilities.getString();
		switch(choice) {
		case ("1"):  
			List<Order> orders = orderDao.readAll();
        	for (Order order: orders) {
        		LOGGER.info(order);
        	}
        return orders;
        
		case ("2"):
			LOGGER.info("Please enter order id");
			Long oid=javaUtilities.getLong();
			List<Item> items= orderDao.readItems(new Order(oid));
			LOGGER.info(orderDao.read(oid));
			for (Item item:items) {
				LOGGER.info(item);
			}
			break;
			
		default: LOGGER.info("Invalid input. Go to DELETE to try again.");
		
		}
		return new ArrayList<>();
	}

	@Override
	public Order update() {
		   LOGGER.info("Please enter the oid of the order you would like to update");
	        Long oid = javaUtilities.getLong();
	        LOGGER.info("What would you like to do in UPDATE"
	        		+ "\n -> (1) Change customer id "
	        		+ "\n -> (2) Add item to order"
	        		+ "\n -> (3) Delete item from order");
	        String choice=javaUtilities.getString();
	        Order order = null;
	        
	        switch (choice) {
	        
	        case ("1"): LOGGER.info("Please enter new customer id");
	        Long fkCid=javaUtilities.getLong();
	        order= orderDao.update(new Order(oid,fkCid,0.0));
	        LOGGER.info("Customer id updated.");
	        return order;
	        
	        case ("2"): LOGGER.info("Please enter iid of item you would like to add");
        	Long iid=javaUtilities.getLong();
        	order=new Order(oid,iid);
        	orderDao.updateAdd(order);
        	LOGGER.info("Item added.");
        	return order;
        	
	        case ("3"): LOGGER.info("Please enter iid of item you would like to delete");
        	Long iid1 =javaUtilities.getLong();
        	order=new Order(oid,iid1);
        	orderDao.updateDelete(order);
        	LOGGER.info("Item deleted.");
        	return order;
        	
	        default: LOGGER.info("Invalid input. Choose update to try again.'");
	        }
			return order;
	     
	}

	@Override
	public int delete() {
		  LOGGER.info("Please enter the id of the order you would like to delete");
	        Long oid = javaUtilities.getLong();
	        LOGGER.info("Order deleted.");
	        return orderDao.delete(oid);
	}
	


}
