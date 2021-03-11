package com.kubaokleja.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UnitTest {
	
	@Test
	void unitShouldNotMoveWithoutFuel() {
		//given
		Unit unit = new Unit(new Coordinates(0,0),0, 10);
		
		//when
		//then
		assertThrows(IllegalStateException.class, () -> unit.move(1, 0));
	}
	
	@Test
	void fuelLevelShouldReduceAfterMove() {
		//given
		Unit unit = new Unit(new Coordinates(0,0),10, 10);
		
		//when
		unit.move(1, 0);
		
		//then
		assertThat(unit.getFuel(), equalTo(9));
	}
	
	@Test
	void coordinatesShouldBeChangedAfterMove() {
		//given
		Unit unit = new Unit(new Coordinates(0,0),10, 10);
		
		//when
		Coordinates coordinates = unit.move(1, 0);
		
		//then
		assertThat(coordinates, equalTo(new Coordinates(1,0)));
	}
	
	@Test
    void fuelingShouldNotExceedMaxFuelLimit() {
        //given
        Unit unit = new Unit(new Coordinates(0,0),10,10);
        //when
        unit.tankUp();
        //then
		assertThat(unit.getFuel(), is(10));
	}
	
	@Test
    void cargoCanNotExceedMaxWeightLimit() {
        //given
        Unit unit = new Unit(new Coordinates(0,0),10,10);
        Cargo cargo = new Cargo("cargo", 11);
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));
	}
	
	@Test
	void cargoWeightShouldBeNullAfterUnloadingAll() {
		//given
        Unit unit = new Unit(new Coordinates(0,0),10,10);
        Cargo cargo = new Cargo("cargo", 8);
        unit.loadCargo(cargo);
        
        //when
        unit.unloadAllCargo();
        
        //then
        assertThat(unit.getCurrentCargoWeight(), is(0));
	}
}
