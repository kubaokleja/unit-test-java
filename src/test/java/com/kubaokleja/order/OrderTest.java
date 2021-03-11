package com.kubaokleja.order;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.kubaokleja.extensions.BeforeAfterExtension;
import com.kubaokleja.meal.Meal;
import com.kubaokleja.order.Order;

@ExtendWith(BeforeAfterExtension.class)
public class OrderTest {
	
	private Order order;
	
	@BeforeEach
	void initializeOrder() {
		System.out.println("Before each");
		order = new Order();
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("After each");
		order.cancel();;
	}
	@Test
	public void testAssertArrayEquals() {
		//given
		int [] intsOne = {1,2,3};
		int [] intsTwo = {1,2,3};
		//then
		assertArrayEquals(intsOne, intsTwo);
	}
	
	@Test
	public void mealListShouldBeEmptyAfterCreationOfOrder() {	
		//then
		assertThat(order.getMeals(), empty());
		assertThat(order.getMeals().size(), equalTo(0));
		assertThat(order.getMeals(), hasSize(0));
		assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
	}
	
	@Test
	public void addingMealToOrderShouldIncreaseOrderSize() {
		//given
		Meal meal = new Meal(20, "Burger");
		
		//when
		order.addMealToOrder(meal);
		
		//then
		assertThat(order.getMeals().size(), equalTo(1));
		assertThat(order.getMeals(), contains(meal));
	}
	@Test
	public void removingMealFromOrderShouldDecreaseOrderSize() {
		//given
		Meal meal = new Meal(20, "Burger");
		
		//when
		order.removeMealFromOrder(meal);
		
		//then
		assertThat(order.getMeals(), hasSize(0));

	}
	@Test
	public void mealsShouldNotBeInOrderAfterAddingThemToOrder() {
		//given
		Meal meal = new Meal(20, "Burger");
		Meal mealTwo = new Meal(25, "Burger Drwala");
		//when
		order.addMealToOrder(meal);
		order.addMealToOrder(mealTwo);
		//then
		assertThat(order.getMeals(), containsInAnyOrder(meal, mealTwo));
	}
	@Test
	public void orderTotalPriceShouldNotExceedsMaxIntValue() {
		//given
		Meal meal = new Meal(Integer.MAX_VALUE, "Burger");
		Meal mealTwo = new Meal(Integer.MAX_VALUE, "Burger Drwala");
		//when
		order.addMealToOrder(meal);
		order.addMealToOrder(mealTwo);
		//then
		assertThrows(IllegalStateException.class, () -> order.totalPrice());
	}
	
	@Test
	void emptyOrderTotalPriceShouldEqualZero() {
		//given
		//Order is created in BeforeEach
		
		//then 
		assertThat(order.totalPrice(), is(0));
	}
}
