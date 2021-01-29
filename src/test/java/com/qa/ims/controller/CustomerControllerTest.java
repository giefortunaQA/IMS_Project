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


import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.JavaUtilities;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	@Mock
	private JavaUtilities javaUtilities;
	
	@Mock
	private CustomerDao customerDao;
	
	@InjectMocks
	private CustomerController customerController;
	
	private Long testCid;
	private String testFirstName;
	private String testSurname;
	private Long testHouseNo;
	private String testPostcode;
	private Customer testCustomer;
	private List<Customer> testCustomers;
	
	@Before
	public void set() {//given
		testCid=1L;
		testFirstName="John";
		testSurname="Smith";
		testHouseNo=1L;
		testPostcode="AAA9 9AA";
		testCustomer=new Customer(testFirstName,testSurname,testHouseNo,testPostcode);
		testCustomers=Arrays.asList(testCustomer);
		}
	
	@Test
	public void testCreate() {
		when(javaUtilities.getString()).thenReturn(testFirstName,testSurname,testPostcode);
		when(javaUtilities.getLong()).thenReturn(testHouseNo);
		when(customerDao.create(testCustomer)).thenReturn(testCustomer);
		assertEquals(testCustomer,customerController.create());
		verify(javaUtilities,Mockito.times(3)).getString();
		verify(javaUtilities,Mockito.times(1)).getLong();
		verify(customerDao,Mockito.times(1)).create(testCustomer);
	}
	
	@Test
	public void testReadAll() {
		when(customerDao.readAll()).thenReturn(testCustomers);
		assertEquals(testCustomers,customerController.readAll());
		verify(customerDao,Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		String updatedFirstName="Mike";
		Customer updatedCustomer=new Customer(testCid,updatedFirstName,testSurname,testHouseNo, testPostcode);
		when(javaUtilities.getString()).thenReturn(updatedFirstName,testSurname,testPostcode);
		when(javaUtilities.getLong()).thenReturn(testCid,testHouseNo);
		when(customerDao.update(updatedCustomer)).thenReturn(updatedCustomer);
		assertEquals(updatedCustomer,customerController.update());
		verify(javaUtilities,Mockito.times(3)).getString();
		verify(javaUtilities,Mockito.times(2)).getLong();
		verify(customerDao,Mockito.times(1)).update(updatedCustomer);
	}
	
	@Test
	public void testDelete() {
		when(javaUtilities.getLong()).thenReturn(testCid);
		when(customerDao.delete(testCid)).thenReturn(testCid.intValue());
		assertEquals(testCid.intValue(),customerController.delete());
		
	}
	
}
