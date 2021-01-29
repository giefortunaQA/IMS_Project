package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.JavaUtilities;


@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	@Mock
	private JavaUtilities javaUtilities;
	
	@Mock
	private ItemDao itemDao;
	
	@InjectMocks
	private ItemController itemController;
	
	private Long testIid;
	private String testName;
	private Double testPrice;
	private Item testItem;
	private List<Item> testItems;
	
	@Before
	public void set() {//given
		testIid=1L;
		testName="bar stool";
		testPrice=49.99;
		testItem=new Item(testName,testPrice);
		testItems=Arrays.asList(testItem);
		}
	
	@Test
	public void testCreate() {
		when(javaUtilities.getString()).thenReturn(testName);
		when(javaUtilities.getDouble()).thenReturn(testPrice);
		when(itemDao.create(testItem)).thenReturn(testItem);
		assertEquals(testItem,itemController.create());
		verify(javaUtilities,Mockito.times(1)).getString();
		verify(javaUtilities,Mockito.times(1)).getDouble();
		verify(itemDao,Mockito.times(1)).create(testItem);
	}
	
	@Test
	public void testReadAll() {
		when(itemDao.readAll()).thenReturn(testItems);
		assertEquals(testItems,itemController.readAll());
		verify(itemDao,Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		String updatedName="stool";
		Item updatedItem=new Item(testIid,updatedName,testPrice);
		when(javaUtilities.getLong()).thenReturn(testIid);
		when(javaUtilities.getString()).thenReturn(updatedName);
		when(javaUtilities.getDouble()).thenReturn(testPrice);
		when(itemDao.update(updatedItem)).thenReturn(updatedItem);
		assertEquals(updatedItem,itemController.update());
		verify(javaUtilities,Mockito.times(1)).getLong();
		verify(javaUtilities,Mockito.times(1)).getString();
		verify(javaUtilities,Mockito.times(1)).getDouble();
		verify(itemDao,Mockito.times(1)).update(updatedItem);
	}
	
	@Test
	public void testDelete() {
		when(javaUtilities.getLong()).thenReturn(testIid);
		when(itemDao.delete(testIid)).thenReturn(testIid.intValue());
		assertEquals(testIid.intValue(),itemController.delete());
		verify(javaUtilities,Mockito.times(1)).getLong();
		verify(itemDao,Mockito.times(1)).delete(testIid);
		
	}

}
