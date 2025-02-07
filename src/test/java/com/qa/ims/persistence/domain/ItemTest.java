package com.qa.ims.persistence.domain;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	

	@Test
	public void testConstruct1() {
		final Item item=new Item(1L,"bar stool",49.99);
		assertEquals(1L, item.getIid().longValue());
		assertEquals("bar stool",item.getName());
		assertEquals(49.99,item.getPrice(),0.001);
	}
	
	@Test
	public void testConstruct2() {
		final Item item=new Item("bar stool",49.99);
		assertEquals("bar stool",item.getName());
		assertEquals(49.99,item.getPrice(),0.001);
	}
	
	@Test
	public void testGettersAndSetters() {
		Item item=new Item(null,null,null);
		item.setIid(1L);
		item.setName("bar stool");
		item.setPrice(49.99);
		assertEquals(1L, item.getIid().longValue());
		assertEquals("bar stool",item.getName());
		assertEquals(49.99,item.getPrice(),0.001);
	}
	
	
	@Test
	public void testToString() {
		final Item item=new Item(1L,"bar stool",49.99);
		String expected="Item [iid=1, name=bar stool, price=49.99]";
		assertEquals(expected,item.toString());
	}
}
