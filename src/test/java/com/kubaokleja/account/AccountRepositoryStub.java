package com.kubaokleja.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository{

	@Override
	public List<Account> getAllAccounts() {
		Address addressOne = new Address("test", "23");
		Account account = new Account(addressOne);
		Account accountTwo = new Account();
		Address addressTwo = new Address("test again", "26");
		Account accountThree = new Account(addressTwo);

		return Arrays.asList(account, accountTwo, accountThree);
	}
	

}
