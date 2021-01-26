package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void testToString() {
		final Order order=new Order(1L, 1L,49.99);
		String expected="Order [oid=1, fkCid=1, orderValue=49.99]";
		assertEquals(expected,order.toString());
	}
}
