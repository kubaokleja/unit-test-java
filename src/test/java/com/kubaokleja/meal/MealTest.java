package com.kubaokleja.meal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kubaokleja.meal.Meal;
import com.kubaokleja.order.Order;

class MealTest {
	
	@Spy
    private Meal mealSpy;
	
	@Test
	void shouldReturnDiscountedPrice() {
		//given
		Meal meal = new Meal(35);
		//when 
		int discountedPrice = meal.getDiscountedPrice(10);
		//then
		assertEquals(25, discountedPrice);
		assertThat(discountedPrice, is(25));
	}
	@Test
	void referencesToTheSameObjectShouldBeEqual() {
		//given
		Meal mealOne = new Meal(25);
		Meal mealTwo = mealOne;
		
		//then
		assertSame(mealOne, mealTwo);
		assertThat(mealOne, sameInstance(mealTwo));
	}
	
	@Test
	void referencesToDifferentObjectsShouldNotBeEqual() {
		//given
		Meal mealOne = new Meal(25);
		Meal mealTwo = new Meal(25);
		
		//then
		assertNotSame(mealOne, mealTwo);
		assertThat(mealOne, not(sameInstance(mealTwo)));

	}
	
	@Test
	void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
		//given 
		Meal mealOne = new Meal(10,"Pizza");
		Meal mealTwo = new Meal(10,"Pizza");
		
		//then
		assertEquals(mealOne, mealTwo);
	}
	
	@Test
	void exceptionShouldBeThrownIfDiscountIsHigherThanActualPrice() {
		//given
		Meal meal = new Meal(10);
		
		//when
		//then
		assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(12));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5, 10, 15, 18})
	void mealPricesShouldBeLowerThan20(int price) {
		assertThat(price, lessThan(20));
	}
	
	@ParameterizedTest
	@MethodSource("createMealsWithNameAndPrice") //@CsvSource({"Hamburger, 10", "Cheeseburger, 12"}) @CsvFileSource(resources = "/meals.csv")
	void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
		assertThat(name, containsString("burger"));
		assertThat(price, greaterThanOrEqualTo(10));
	}
	
	@ParameterizedTest
	@MethodSource("createCakeNames")
	void cakeNamesShouldEndWithCake(String name) {
		assertThat(name, notNullValue());
		assertThat(name, endsWith("cake"));
	}
	
	@TestFactory
	Collection<DynamicTest> dynamicTestCollection(){
		return Arrays.asList(
				dynamicTest("Dynamic test1",()->assertThat(5, lessThan(6))),
				dynamicTest("Dynamic test2",()->assertEquals(4, 2*2))
		);
	}
	
	@Tag("fries")
	@TestFactory
	Collection<DynamicTest> calculateMealPrices(){
		Order order = new Order();
		order.addMealToOrder(new Meal(10,2,"Hamburger"));
		order.addMealToOrder(new Meal(7,4,"Fries"));
		order.addMealToOrder(new Meal(22,3,"Pizza"));
		
		Collection<DynamicTest> dynamicTests = new ArrayList<>();
		for(int i=0;i<order.getMeals().size();i++) {
			int price = order.getMeals().get(i).getPrice();
			int quantity = order.getMeals().get(i).getQuantity();
			
			Executable executable = () -> {
				assertThat(calculatePrice(price,quantity), lessThan(67));
			};
			
			String name = "Test name: " + i;
			DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
			dynamicTests.add(dynamicTest);
		}
		
		return dynamicTests;
	}
	
	@Test 
	@ExtendWith(MockitoExtension.class)
	void testMealSumPriceWithSpy() {
		//given
		given(mealSpy.getPrice()).willReturn(15);
		given(mealSpy.getQuantity()).willReturn(3);

		//when
		int result = calculatePrice(mealSpy.getPrice(),mealSpy.getQuantity());
		
		//then
		assertThat(result, equalTo(45));
	}
	
	private int calculatePrice(int price, int quantity) {
		return price*quantity;
	}
	
	private static Stream<Arguments> createMealsWithNameAndPrice(){
		return Stream.of(
			Arguments.of("Hamburger", 10),
			Arguments.of("Cheeseburger", 12)
		);	
	}

	private static Stream<String> createCakeNames(){
		List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
		return cakeNames.stream();
	}
}
