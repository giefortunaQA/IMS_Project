package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.JavaUtilities;

public class CustomerController implements ICrudController<Customer> {

    public static final Logger LOGGER = LogManager.getLogger();

    private CustomerDao customerDao;
    private JavaUtilities javaUtilities;

    public CustomerController(CustomerDao customerDao, JavaUtilities javaUtilities) {
        super();
        this.customerDao = customerDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public Customer create() {
        LOGGER.info("Please enter a first name");
        String firstName = javaUtilities.getString();
        LOGGER.info("Please enter a surname");
        String surname = javaUtilities.getString();
        LOGGER.info("Please enter a house number");
        Long houseNumber=javaUtilities.getLong();
        LOGGER.info("Please enter a postcode (AAA9 9AA format) ");
        String postCode=javaUtilities.getString();
        Customer customer = customerDao.create(new Customer(firstName, surname,houseNumber,postCode));
        LOGGER.info("Customer created");
        return customer;
    }

    @Override
    public List<Customer> readAll() {
        List<Customer> customers = customerDao.readAll();
        for (Customer customer : customers) {
            LOGGER.info(customer);
        }
        return customers;
    }

    @Override
    public Customer update() {
        LOGGER.info("Please enter the cid of the customer you would like to update");
        Long cid = javaUtilities.getLong();
        LOGGER.info("Please enter a first name");
        String firstName = javaUtilities.getString();
        LOGGER.info("Please enter a surname");
        String surname = javaUtilities.getString();
        LOGGER.info("Please enter a house number");
        Long houseNumber=javaUtilities.getLong();
        LOGGER.info("Please enter a postcode (AAA9 9AA format) ");
        String postCode=javaUtilities.getString();
        Customer customer = customerDao.update(new Customer(cid,firstName, surname,houseNumber,postCode));
        LOGGER.info("Customer Updated");
        return customer;
    	
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the customer you would like to delete");
        Long id = javaUtilities.getLong();
        return customerDao.delete(id);
    }

}
