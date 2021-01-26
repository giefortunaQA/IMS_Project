package com.qa.ims.persistence.domain;


public class Order {
	private Long oid;
	private Long fkCid;
	private Double orderValue;
	private Long iid;
	
	public Order(Long oid) {
		this.oid=oid;
	}
	
	public Order(Long oid,Long iid) {
		this.oid=oid;
		this.setIid(iid);
	}
	
	public Order(Long fkCid, Double orderValue) {
		super();
		this.fkCid = fkCid;
		this.orderValue = orderValue;
	}
	public Order(Long oid, Long fkCid, Double orderValue) {
		super();
		this.oid = oid;
		this.fkCid = fkCid;
		this.orderValue = orderValue;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Long getFkCid() {
		return fkCid;
	}
	public void setFkCid(Long fkCid) {
		this.fkCid = fkCid;
	}
	public Double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", fkCid=" + fkCid + ", orderValue=" + orderValue + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkCid == null) ? 0 : fkCid.hashCode());
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
		if (fkCid == null) {
			if (other.fkCid != null)
				return false;
		} else if (!fkCid.equals(other.fkCid))
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

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

}
