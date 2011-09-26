package com.bfb.commonservice.client;

import com.bfb.commonservice.service.AccountService;

public class SimpleObject {
	private AccountService actService;
	private AccountService hesActService;
	
	public AccountService getHesActService() {
		return hesActService;
	}

	public void setHesActService(AccountService hesActService) {
		this.hesActService = hesActService;
	}

	public AccountService getActService() {
		return actService;
	}

	public void setActService(AccountService actService) {
		this.actService = actService;
	}

	
}
