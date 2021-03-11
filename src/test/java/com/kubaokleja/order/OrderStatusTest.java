package com.kubaokleja.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.kubaokleja.order.OrderStatus;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class OrderStatusTest {

	@ParameterizedTest
	@EnumSource(OrderStatus.class)
	void allOrderStatusStatusShouldBeShorterThan15Chars(OrderStatus orderStatus) {
		assertThat(orderStatus.toString().length(), lessThan(15));
	}

}
