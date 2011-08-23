package com.bfb.drool.model.mix;

public class Account {
	private long accountNo;
	public double balance;
	
	public Account(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
