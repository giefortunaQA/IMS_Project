package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDao;
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
//		boolean keepAdd=true;
//        List<Long> itemList= new ArrayList<Long>();
        LOGGER.info("Please enter customer id");
        Long fkCid = javaUtilities.getLong();
        
//        while (keepAdd) {
//        	LOGGER.info("Please enter item id to order\n >> '0' to stop adding");
//            Long fkIid=javaUtilities.getLong();
//            if (fkIid!=0) {
//            	itemList.add(fkIid);
//            	LOGGER.info("Added!");
//            } else {
//            	keepAdd=false;
//            	}
//        }
        
        Order order = orderDao.create(new Order(fkCid,null));
//        for (Long iid: itemList) {
//        	orderDao.addToOrder(order.getOid(), iid);
//        }
        LOGGER.info("Order record created.");
        return order;
	}

	@Override
	public List<Order> readAll() {
		 List<Order> orders = orderDao.readAll();
	        for (Order order: orders) {
	            LOGGER.info(order);
	        }
	        return orders;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
