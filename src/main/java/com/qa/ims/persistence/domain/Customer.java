package com.qa.ims.persistence.domain;

public class Customer {

	private Long cid ;
	private String firstName;
	private String surname;
	private Long houseNumber;
	private String postCode;

	public Customer(String firstName, String surname,Long houseNumber,String postCode) {
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.houseNumber=houseNumber;
		this.postCode=postCode;
	}

	public Customer(Long cid, String firstName, String surname,Long houseNumber,String postCode) {
		this.setCid(cid);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.houseNumber=houseNumber;
		this.postCode=postCode;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Long houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", firstName=" + firstName + ", surname=" + surname + ", houseNumber="
				+ houseNumber + ", postCode=" + postCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Customer other = (Customer) obj;
		if (getFirstName() == null) {
			if (other.getFirstName() != null)
				return false;
		} else if (!getFirstName().equals(other.getFirstName()))
			return false;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
