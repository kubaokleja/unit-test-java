package com.kubaokleja.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {

	private List<Meal> meals = new ArrayList<>();

	public void add(Meal meal) {
		meals.add(meal);
	}

	public List<Meal> getAllMeals() {
		return meals;
	}

	public void delete(Meal meal) {
		meals.remove(meal);
	}

	public List<Meal> findByName(String mealName, boolean exactMatch) {
		
		List<Meal> result = new ArrayList<>();
		if(exactMatch) {
			result = meals.stream()
					.filter(meal -> meal.getName().equals(mealName))
					.collect(Collectors.toList());
		}
		else {
			result = meals.stream()
					.filter(meal -> meal.getName().startsWith(mealName))
					.collect(Collectors.toList());
		}
		return result;
	}

	public List<Meal> findByPriceWithInitialData(int price, SearchType searchType, List<Meal> meals) {
		List<Meal> result = new ArrayList<>();
		if(searchType.equals(SearchType.EXACT)) {
			result = meals.stream()
				.filter(meal -> meal.getPrice() == price)
				.collect(Collectors.toList());
		}
		else if(searchType.equals(SearchType.LESSER)) {
			result = meals.stream()
				.filter(meal -> meal.getPrice() < price)
				.collect(Collectors.toList());
		}
		else if(searchType.equals(SearchType.HIGHER)) {
			result = meals.stream()
				.filter(meal -> meal.getPrice() > price)
				.collect(Collectors.toList());
		}
		return result;
	}
	
	public List<Meal> findByPrice(int price, SearchType searchType) {
		return findByPriceWithInitialData(price, searchType, meals);
	}
	
	public List<Meal> find(String string, boolean exactMatch, int price, SearchType searchType) {
		List<Meal> mealsByName = findByName(string,exactMatch);
		return findByPriceWithInitialData(price, searchType, mealsByName);
	}

}
