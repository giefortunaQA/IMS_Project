package com.qa.ims.persistence.domain;

public class Order {
	private Long oid;
	private Long fkCid;
	
	public Order(Long oid, Long fkCid) {
		this.oid=oid;
		this.fkCid=fkCid;
	}
	
	public Order(Long fkCid) {
		this.fkCid=fkCid;
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

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", fkCid=" + fkCid + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((fkCid == null) ? 0 : fkCid.hashCode());
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
		if (getOid() == null) {
			if (other.getOid() != null)
				return false;
		} else if (!getOid().equals(other.getOid()))
			return false;
		if (fkCid == null) {
			if (other.fkCid != null)
				return false;
		} else if (!fkCid.equals(other.fkCid))
			return false;
		return true;
	}
	
	
}
