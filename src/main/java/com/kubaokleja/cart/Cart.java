package com.kubaokleja.cart;

import java.util.ArrayList;
import java.util.List;

import com.kubaokleja.meal.Meal;
import com.kubaokleja.order.Order;

public class Cart {
	List<Order> orders = new ArrayList<>();
	
	void addOrderToCart(Order order) {
		this.orders.add(order);
	}
	
	void clearCart() {
		this.orders.clear();
	}
	
	void simulateLargeOrder() {
		for(int i = 0; i<1000;i++) {
			Meal meal = new Meal(i%10, "Burger nr: "+i);
			Order order = new Order ();
			order.addMealToOrder(meal);
			addOrderToCart(order);
		}
		System.out.println("Cart size: "+ orders.size());
		clearCart();
	}

	public List<Order> getOrders() {
		return orders;
	}
	
}
