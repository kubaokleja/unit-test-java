package com.kubaokleja.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.kubaokleja.account.Account;
import com.kubaokleja.account.Address;

class AccountTest {

	@Test
	public void newAccountShouldNotBeActive() {
		//given
		//when
		Account newAccount = new Account();
		//then
		assertFalse(newAccount.isActive());
		assertThat(newAccount.isActive(), equalTo(false));
		assertThat(newAccount.isActive(), is(false));
	}
	
	@Test
	public void accountShouldBeActiveAfterActivation() {
		//given
		Account newAccount = new Account();
		//when
		newAccount.activate();
		//then
		assertTrue(newAccount.isActive());
	}
	
	@Test
	public void createdAccountShouldNotHaveSefaultDeliveryAddressSet(){
		//given
		Account account = new Account();
		
		//when
		Address address = account.getDefaultDeliveryAddress();
		
		//then
		assertNull(address);
		assertThat(address, nullValue());
	}
	
	@Test
	public void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
		//given
		Address address = new Address("Swatowska","321321");
		Account account = new Account();
		account.setDefaultDeliveryAddress(address);
		
		//when
		Address defaultAddress = account.getDefaultDeliveryAddress();
		
		//then
		assertNotNull(defaultAddress);
		assertThat(defaultAddress, is(notNullValue()));

	}
	
	@RepeatedTest(5)
	void newAccountWithNotNullAdressShouldBeActive() {
		//given
		Address address = new Address("Puławska", "21");
		
		//when
		Account account = new Account(address);
		
		//then
		assumingThat(address != null , () ->{
			assertTrue(account.isActive());
		});
		
	}
	
	@Test
	void invalidEmailShouldThrowException() {
		//given 
		Account account = new Account();
		//when		
		//then
		assertThrows(IllegalArgumentException.class, ()->account.setEmail("wrongEmail"));
	}
	
	@Test
	void validEmailShouldBeSet() {
		//given 
		Account account = new Account();
		//when
		account.setEmail("test@test.com");
		//then
		assertThat(account.getEmail(), is("test@test.com"));
	}
}
