package com.qa.ims.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.JavaUtilities;

public class ItemController implements ICrudController<Item> {
	public static final Logger LOGGER = LogManager.getLogger();

    private ItemDao itemDao;
    private JavaUtilities javaUtilities;

    public ItemController(ItemDao itemDao, JavaUtilities javaUtilities) {
        super();
        this.itemDao = itemDao;
        this.javaUtilities = javaUtilities;
    }

    @Override
    public Item create() {
    	LOGGER.info("Please enter an item name");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter quantity of this item");
        long qty = javaUtilities.getLong();
        LOGGER.info("Please enter the price of this item");
        double price=javaUtilities.getDouble();
        Item item = itemDao.create(new Item(name,qty,price));
        LOGGER.info("Item record created.");
        return item;
    }
    
    @Override
    public List<Item> readAll() {
        return null;
    }
    @Override
    public Item update() {
       return null;
    }

    @Override
    public int delete() {
    	return 0;
//        LOGGER.info("Please enter the id of the customer you would like to delete");
//        Long id = javaUtilities.getLong();
//        return customerDao.delete(id);
    }

}
