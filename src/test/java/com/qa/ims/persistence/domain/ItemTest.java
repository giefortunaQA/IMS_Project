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
	public void testToString() {
		final Item item=new Item(1L,"bar stool",49.99);
		String expected="Item [iid=1, name=bar stool, price=49.99]";
		assertEquals(expected,item.toString());
	}
}
