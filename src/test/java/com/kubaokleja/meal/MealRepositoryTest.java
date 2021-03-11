package com.kubaokleja.meal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MealRepositoryTest {
	
	MealRepository mealRepository = new MealRepository();
	
	@BeforeEach
	void cleanUp() {
		mealRepository.getAllMeals().clear();
	}
	
	@Test
	void shouldBeAbleToAddMealToRepository() {
		//given
		Meal meal = new Meal (10, "Pizza");
		//when
		mealRepository.add(meal);
		
		//then 
		assertThat(mealRepository.getAllMeals().get(0), is(meal));
	}
	
	@Test
	void shouldBeAbleToRemoveMealFromRepository() {
		//given
		Meal meal = new Meal (10, "Pizza");
		mealRepository.add(meal);
		//when
		mealRepository.delete(meal);
		
		//then
		assertThat(mealRepository.getAllMeals(), not(contains(meal)));
	}
	
	@Test
	void shouldBeAbleToFindMealByExactName() {
		//given
		Meal meal = new Meal (10, "Pizza");
		Meal mealTwo = new Meal (15, "Pizza Capriciosa");
		mealRepository.add(meal);
		mealRepository.add(mealTwo);

		//when
		List<Meal> results = mealRepository.findByName("Pizza", true);
		
		//then
		assertThat(results.size(),is(1));
	}
	
	@Test
	void shouldBeAbleToFindMealByStartingLettersName() {
		//given
		Meal meal = new Meal (10, "Pizza");
		Meal mealTwo = new Meal (15, "Pizza Capriciosa");
		mealRepository.add(meal);
		mealRepository.add(mealTwo);

		//when
		List<Meal> results = mealRepository.findByName("Pizza", false);
		
		//then
		assertThat(results.size(),is(2));
	}
	
	@Test
	void shouldBeAbleToFindMealByExactPrice() {
		//given
		Meal meal = new Meal (10, "Pizza");
		Meal mealTwo = new Meal (15, "Pizza Capriciosa");
		mealRepository.add(meal);
		mealRepository.add(mealTwo);
		
		//when
		List<Meal> results = mealRepository.findByPrice(10, SearchType.EXACT);
		
		//then
		assertThat(results.size(),is(1));
	}
	
	@Test
	void shouldBeAbleToFindMealByLesserPrice() {
		//given
		Meal meal = new Meal (10, "Pizza");
		Meal mealTwo = new Meal (15, "Pizza Capriciosa");
		mealRepository.add(meal);
		mealRepository.add(mealTwo);
		
		//when
		List<Meal> results = mealRepository.findByPrice(9, SearchType.LESSER);
		
		//then
		assertThat(results.size(),is(0));
	}
	
	@Test
	void shouldBeAbleToFindMealByHigherPrice() {
		//given
		Meal meal = new Meal (10, "Pizza");
		Meal mealTwo = new Meal (15, "Pizza Capriciosa");
		mealRepository.add(meal);
		mealRepository.add(mealTwo);
		
		//when
		List<Meal> results = mealRepository.findByPrice(9, SearchType.HIGHER);
		
		//then
		assertThat(results.size(),is(2));
	}
	
	@Test
    void shouldFindByExactNameAndExactPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 10, SearchType.EXACT);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }
	

    @Test
    void shouldFindByFirstLetterAndExactPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 9, SearchType.EXACT);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndLowerPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 11, SearchType.LESSER);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndLowerPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 10, SearchType.LESSER);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndHigherPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 9, SearchType.HIGHER);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndHigherPrice() {
        //given
        Meal meal = new Meal(10, "Pizza");
        Meal meal2 = new Meal(9, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 8, SearchType.HIGHER);

        //then
        assertThat(results.size(),is(1));
        assertThat(results.get(0), is(meal2));
    }
	
}
