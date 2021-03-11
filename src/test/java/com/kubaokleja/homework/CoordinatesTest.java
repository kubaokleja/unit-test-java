package com.kubaokleja.homework;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {
	
	@Test
	void exceptionShouldBeThrownWhenAnyCoordinateIsNegative() {
		//given 
		//when		
		//then
		assertThrows(IllegalArgumentException.class, () -> new Coordinates(-44, 3));
	}

	@Test
	void exceptionShouldBeThrownWhenAnyCoordinateIsAbove100() {
		//given 
		//when		
		//then
		assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 3));
	}
	
	@Test
	void copyShouldReturnNewObject() {
		//given
		Coordinates coordinates = new Coordinates(4,4);
		
		//when 
		Coordinates copy = Coordinates.copy(coordinates, 0, 0);
		
		//then
		assertThat(copy, not(sameInstance(coordinates)));
		assertThat(copy, equalTo(coordinates));
	}
	
	@Test
	void copyShouldReturnAddCoordinates() {
		//given
		Coordinates coordinates = new Coordinates(4,4);
		int x = 4;
		int y = 3;
		//when 
		Coordinates copy = Coordinates.copy(coordinates, x, y);
		
		//then
		assertThat(copy.getX(), equalTo(coordinates.getX()+x));
		assertThat(copy.getY(), equalTo(coordinates.getY()+y));
	}
}
