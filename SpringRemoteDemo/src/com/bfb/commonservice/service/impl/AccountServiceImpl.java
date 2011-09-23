package com.bfb.commonservice.service.impl;

import java.util.List;

import com.bfb.commonservice.model.Account;
import com.bfb.commonservice.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Override
	public List<Account> getAccounts(String name) {
		return null;
	}

	@Override
	public int insertAccount(Account account) {
		System.out.println(account.getName());
		return 1;
	}

}
