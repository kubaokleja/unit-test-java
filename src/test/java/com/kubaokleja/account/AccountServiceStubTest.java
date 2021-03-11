package com.kubaokleja.account;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
class AccountServiceStubTest {

	@Test
	void getAllActiveAccounts() {
		//given
		AccountRepository accountRepoStub = new AccountRepositoryStub();
		AccountService accountService = new AccountService(accountRepoStub);
		
		//when
		List<Account> activeAccountsList = accountService.getAllActiveAccounts();
		
		//then
		assertThat(activeAccountsList, hasSize(2));
	}

}
