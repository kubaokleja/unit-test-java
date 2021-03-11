package com.kubaokleja.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AccountServiceTest {
	@Test
	void getAllActiveAccounts() {
		//given
		List<Account> accounts = prepareAccountData();
		AccountRepository accountRepository = mock(AccountRepository.class);
		AccountService accountService = new AccountService(accountRepository);
		when(accountRepository.getAllAccounts()).thenReturn(accounts);
		
		//when
		List<Account> activeAccountsList = accountService.getAllActiveAccounts();
		
		//then
		assertThat(activeAccountsList, hasSize(2));
	}
	
	@Test
	void getNoActiveAccounts() {
		//given
		AccountRepository accountRepository = mock(AccountRepository.class);
		AccountService accountService = new AccountService(accountRepository);
		when(accountRepository.getAllAccounts()).thenReturn(Arrays.asList());
		
		//when
		List<Account> activeAccountsList = accountService.getAllActiveAccounts();
		
		//then
		assertThat(activeAccountsList, hasSize(0));
	}
	private List<Account> prepareAccountData(){
		Address addressOne = new Address("test", "23");
		Account account = new Account(addressOne);
		Account accountTwo = new Account();
		Address addressTwo = new Address("test again", "26");
		Account accountThree = new Account(addressTwo);

		return Arrays.asList(account, accountTwo, accountThree);
	}
}
