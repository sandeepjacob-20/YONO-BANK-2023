package com.nissan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	private long accNo;
	
	@Column(name="custname")
	private String custName;
	
	@Column(name="acctype")
	private String accType;
	
	@Column(name="balance")
	private float balance;
	
//	@Override
//	public String toString() {
//		return "Customer [accNo=" + accNo + ", custName=" + custName + ", accType=" + accType + ", balance=" + balance
//				+ ", minBalance=" + minBalance + ", mobileNumber=" + mobileNumber + ", mailID=" + mailID + ", Pin="
//				+ Pin + ", isActive=" + isActive + "]";
//	}

	@Column(name="minbalance")
	private float minBalance;
	
	@Column(name="mobile")
	private String mobileNumber;
	
	@Column(name="mailid")
	private String mailID;
	
	@Column(name="atmpin")
	private String Pin;
	
	//check the status of user
	private boolean isActive = true;

	public Customer() {
		
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(float minBalance) {
		this.minBalance = minBalance;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	public String getPin() {
		return Pin;
	}

	public void setPin(String pin) {
		Pin = pin;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
}
