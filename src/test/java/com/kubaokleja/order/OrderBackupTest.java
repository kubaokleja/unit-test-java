package com.kubaokleja.order;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.kubaokleja.meal.Meal;
import com.kubaokleja.order.Order;
import com.kubaokleja.order.OrderBackup;

class OrderBackupTest {

	private static OrderBackup orderBackup;
	
	@BeforeAll
	static void setup() throws FileNotFoundException {
		orderBackup = new OrderBackup();
		orderBackup.createFile();
	}
	@AfterAll
	static void tearDown() throws IOException {
		orderBackup.closeFile();
	}
	@Tag("fries")
	@Test
	void backupOrderWithOneMeal() throws IOException {
		//given
		Meal meal = new Meal(7,"Fries");
		Order order = new Order();
		order.addMealToOrder(meal);
		
		//when
		orderBackup.backupOrder(order);
		
		//then
		System.out.println(order.toString() + " backed up");
	}

}
