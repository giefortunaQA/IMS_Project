package com.qa.ims.persistence.dao;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DatabaseUtilities;
public class OrderDaoTest {
	private final CustomerDao CDAO=new CustomerDao();
	private final ItemDao IDAO=new ItemDao();
	private final OrderDao DAO=new OrderDao();
	 @Before
	    public void setup() {
	        DatabaseUtilities.connect();
	        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	    }
	 
	 @Test
	    public void testCreate() {
		 final Order order=new Order(2L, 1L,0.0);
		 assertEquals(order,DAO.create(order));
	    }

	 
	    @Test
	    public void testReadAll() {
	        List<Order> expected = new ArrayList<>();
	        expected.add(new Order(1L, 1L,49.99));
	        assertEquals(expected, DAO.readAll());
	    }

	    @Test
	    public void testReadItems() {
	    	List<Item> expected=new ArrayList<>();
	    	expected.add(new Item(1L,"bar stool",49.99));
	    	assertEquals(expected,DAO.readItems(DAO.read(1L)));
	    }
	    @Test
	    public void testReadLatest() {
	        assertEquals(new Order(1L, 1L,49.99), DAO.readLatest());
	    }

	    @Test
	    public void testRead() {
	        final long ID = 1L;
	        assertEquals(new Order(1L, 1L,49.99), DAO.read(ID));
	    }

	    @Test
	    public void testUpdate() {
	    	CDAO.create(new Customer(2L,"John","Smith",1L,"AAA9 9AA"));
			 final Order order=new Order(1L, 2L,49.99);
			 assertEquals(order,DAO.update(order));
	    }

	    @Test
	    public void testUpdateAdd() {
	    	Order order=new Order(1L,null,0.0); //making order that has oid of order we're adding to
	    	order.setIid(1L); //setting iid of this order chooses what item to add
	    	List<Item> itemList=new ArrayList<>();
	    	itemList.add(IDAO.read(order.getIid()));//item added in sql-data.sql
	    	itemList.add(IDAO.read(order.getIid()));//item added in next line
	    	DAO.updateAdd(order);
	    	assertEquals(itemList,DAO.readItems(DAO.read(order.getOid())));
	    }
	    
	    @Test
	    public void testUpdateDelete() {
	    	Order order=new Order(1L,null,0.0); //making order to get oid from
	    	order.setIid(1L); //set iid to be deleted
	    	List<Item> itemList=new ArrayList<>();
	    	DAO.updateDelete(order);
	    	assertEquals(itemList,DAO.readItems(DAO.read(order.getOid())));
	    }
	    
	    @Test
	    public void testDelete() {
	        assertEquals(1, DAO.delete(1));
	    }
	    
	    @Test
	    public void testGetValue() {
	    	Order order=new Order(1L,null,0.0);
	    	Order expected=new Order(1L,null,49.99);
	    	assertEquals(expected,DAO.getValue(order));
	    }
	    
	    @Test
	    public void testSetValue() {
	    	Order order=new Order(1L,null,99.98);
	    	Order expected=new Order(1L,1L,99.98);
	    	DAO.setValue(order);;
	    	assertEquals(expected,DAO.read(1L));
	    }
}
