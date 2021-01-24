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
        List<Item> items = itemDao.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }
    
    @Override
    public Item update() {
        LOGGER.info("Please enter the iid of the item you would like to update");
        Long iid = javaUtilities.getLong();
        LOGGER.info("Please enter an item name");
        String name = javaUtilities.getString();
        LOGGER.info("Please enter a quantity of this item");
        long qty = javaUtilities.getLong();
        LOGGER.info("Please enter new price of this item");
        double price=javaUtilities.getDouble();
        Item item = itemDao.update(new Item(iid, name, qty,price));
        LOGGER.info("Item record updated.");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the iid of the item you would like to delete");
        Long iid = javaUtilities.getLong();
        LOGGER.info("Item deleted.");
        return itemDao.delete(iid);
        
    }

}
