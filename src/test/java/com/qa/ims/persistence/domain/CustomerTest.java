package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

//	@Test
//	public void testEquals() {
//		EqualsVerifier.simple().forClass(Customer.class).verify();
//	}
	
	@Test
	public void testConstruct1() {
		final Customer customer=new Customer("John","Smith",1L,"AAA9 9AA");
		assertEquals("John",customer.getFirstName());
		assertEquals("Smith",customer.getSurname());
		assertEquals(1L,customer.getHouseNumber().longValue());
		assertEquals("AAA9 9AA",customer.getPostCode());
		
	}
	
	@Test
	public void testConstruct2() {
		final Customer customer=new Customer(1L,"John","Smith",1L,"AAA9 9AA");
		assertEquals(1L,customer.getCid().longValue());
		assertEquals("John",customer.getFirstName());
		assertEquals("Smith",customer.getSurname());
		assertEquals(1L,customer.getHouseNumber().longValue());
		assertEquals("AAA9 9AA",customer.getPostCode());
		
	}
	
	@Test
	public void testGettersAndSetters() {
		Customer customer=new Customer(null,null,null,null,null);
		customer.setCid(1L);
		customer.setFirstName("John");
		customer.setSurname("Smith");
		customer.setHouseNumber(1L);
		customer.setPostCode("AAA9 9AA");
		assertEquals(1L,customer.getCid().longValue());
		assertEquals("John",customer.getFirstName());
		assertEquals("Smith",customer.getSurname());
		assertEquals(1L,customer.getHouseNumber().longValue());
		assertEquals("AAA9 9AA",customer.getPostCode());
	}
	
	@Test
	public void testToString() {
		final Customer customer=new Customer(1L,"John","Smith",1L,"AAA9 9AA");
		String expected="Customer [cid=" + 1L + ", firstName=John, surname=Smith, houseNumber="+1L+", postCode=AAA9 9AA]";
		assertEquals(expected,customer.toString());
	}
	

}
