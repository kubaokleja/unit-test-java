package com.kubaokleja.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kubaokleja.cart.Cart;
import com.kubaokleja.order.Order;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Test cases for Cart")
class CartTest {

	@Disabled
	@Test
	void testSimulateLargeOrder() {
		//given
		Cart cart = new Cart();
		
		//when
		//then
		assertTimeout(Duration.ofMillis(1), cart::simulateLargeOrder);
	}
	
	@Test
	void cartShouldNotBeEmptyAfterAddingOrderToCart() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		
		//when 
		cart.addOrderToCart(order);
		
		//then
		assertThat(cart.getOrders(), allOf(
				notNullValue(),
				hasSize(1),
				is(not(empty())),
				is(not(emptyCollectionOf(Order.class)))
		));
		assertAll(
				()->assertThat(cart.getOrders(),notNullValue()),
				()->assertThat(cart.getOrders(), hasSize(1))
		);
	}
}
