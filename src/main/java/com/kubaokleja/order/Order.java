package com.kubaokleja.order;

import java.util.ArrayList;
import java.util.List;

import com.kubaokleja.meal.Meal;

public class Order {
	
	private OrderStatus orderStatus;
	private List<Meal> meals = new ArrayList<>();
	
	public void addMealToOrder(Meal meal) {
		this.meals.add(meal);
	}
	
	public void removeMealFromOrder(Meal meal) {
		this.meals.remove(meal);
	}
	
	public void changeOrderStatus(OrderStatus orderStatus) {
		this.orderStatus=orderStatus;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public List<Meal> getMeals() {
		return meals;
	}
	
	public void cancel() {
		this.meals.clear();
	}

	public int totalPrice() {
		int sum = 0;
		sum = this.meals.stream().mapToInt(meal -> meal.getPrice()).sum();
		if(sum<0) {
			throw new IllegalStateException("Price limit exceeded");
		}
		else
		{
			return sum;
		}
	}
	
	@Override
	public String toString() {
		return "Order [meals=" + meals + "]";
	}
	
}
