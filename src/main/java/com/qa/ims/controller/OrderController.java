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
        LOGGER.info("Please enter customer id");
        Long fkCid = javaUtilities.getLong();
        Order order = orderDao.create(new Order(fkCid));
        LOGGER.info("Order record created.");
        return order;
	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
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
