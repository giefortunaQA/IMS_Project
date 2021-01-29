package com.qa.ims.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.ItemDao;
import com.qa.ims.persistence.dao.OrderDao;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.JavaUtilities;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private JavaUtilities javaUtilities;
	
	@Mock
	private OrderDao orderDao;
	
	@Mock
	private ItemDao itemDao;
	
	@InjectMocks
	private OrderController orderController;
	
	private Long testOid;
	private Long testFkCid;
	private Double testOrderValue;
	private Long testIid;
	private Order testOrder;
	private List<Order> testOrders;
	private Item testItem;
	private List<Item> testItemInOrder;
	
	@Before
	public void set() {//given
		testOid=1L;
		testFkCid=1L;
		testOrderValue=0.0;
		testIid=1L;
		testOrder=new Order(testFkCid,testOrderValue);
		testOrders=Arrays.asList(testOrder);
		testItemInOrder=new ArrayList<>();
	}
	
	@Test
	public void testCreate1() {//only create order, no items added
		when(javaUtilities.getLong()).thenReturn(testFkCid,0L);
		when(orderDao.create(testOrder)).thenReturn(testOrder);
		assertEquals(testOrder,orderController.create());
		verify(javaUtilities,Mockito.times(2)).getLong();
		verify(orderDao,Mockito.times(1)).create(testOrder);
	}
	
	@Test
	public void testReadAll1() {
		when(javaUtilities.getString()).thenReturn("1");
		when(orderDao.readAll()).thenReturn(testOrders);
		assertEquals(testOrders,orderController.readAll());
		verify(javaUtilities,Mockito.times(1)).getString();
		verify(orderDao,Mockito.times(1)).readAll();
	}
//	
//	@Test
//	public void testReadAll2() {
//		when(javaUtilities.getString()).thenReturn("2");
//		when(javaUtilities.getLong()).thenReturn(testOid);
//		when(orderDao.readItems(testOrder)).thenReturn(testItemInOrder);
//		when(orderDao.read(testOid)).thenReturn(testOrder);
//		
//		String expected=testOrder+"\n"+testItem;
//		assertEquals(new ArrayList<>(),orderController.readAll());
//		
//		verify(javaUtilities,Mockito.times(1)).getString();
//		verify(javaUtilities,Mockito.times(1)).getLong();
//		verify(orderDao,Mockito.timeout(1)).readItems(testOrder);
//		verify(orderDao,Mockito.times(1)).read(testOid);
	
	@Test
	public void testUpdate1() {
			Long updatedFkCid=2L;
			Order updatedOrder=new Order(testOid,updatedFkCid,0.0);
			when(javaUtilities.getString()).thenReturn("1");
			when(javaUtilities.getLong()).thenReturn(testOid,updatedFkCid);
			when(orderDao.update(updatedOrder)).thenReturn(updatedOrder);
			assertEquals(updatedOrder,orderController.update());
			verify(javaUtilities,Mockito.times(2)).getLong();
			verify(javaUtilities,Mockito.times(1)).getString();
			verify(orderDao,Mockito.times(1)).update(updatedOrder);
	}
	
	@Test
	public void testDelete() {
		when(javaUtilities.getLong()).thenReturn(testOid);
		when(orderDao.delete(testOid)).thenReturn(testOid.intValue());
		assertEquals(testOid.intValue(),orderController.delete());
		verify(javaUtilities,Mockito.times(1)).getLong();
		verify(orderDao,Mockito.times(1)).delete(testOid);
		
		
	}
		
		
	
	
}
