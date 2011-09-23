package com.bfb.commonservice.service;

import java.util.List;

import com.bfb.commonservice.model.Account;

public interface AccountService {
	public int insertAccount(Account account);
	public List<Account> getAccounts(String name);
}
