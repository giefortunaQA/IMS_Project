package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {
	private Long oid;
	private Customer customer;
	private List<Item> itemList;
	private Double orderValue;
	
	
	public Order(Long oid, Customer customer, List<Item> itemList, Double orderValue) {
		super();
		this.oid = oid;
		this.customer = customer;
		this.itemList = itemList;
		this.orderValue = orderValue;
		
	}

	
	public Order(Customer customer, List<Item> itemList, Double orderValue) {
		super();
		this.customer = customer;
		this.itemList = itemList;
		this.orderValue = orderValue;
	}


	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public Double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}

	@Override
	public String toString() {
		return "Order [customer=" + customer + ", itemList=" + itemList + ", orderValue=" + orderValue + ", oid=" + oid
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((orderValue == null) ? 0 : orderValue.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (orderValue == null) {
			if (other.orderValue != null)
				return false;
		} else if (!orderValue.equals(other.orderValue))
			return false;
		return true;
	}
	
}
