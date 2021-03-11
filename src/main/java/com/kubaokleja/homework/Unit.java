package com.kubaokleja.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Unit {
	 
    private static Random random = new Random();
 
    private Coordinates coordinates;
    private int fuel;
    private int maxFuel;
    private List<Cargo> cargo;
    private int maxCargoWeight;
    private int currentCargoWeight;
 
    Unit(Coordinates startCoordinates, int maxFuel, int maxCargoWeight) {
 
        this.coordinates = startCoordinates;
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
        this.maxCargoWeight = maxCargoWeight;
        this.currentCargoWeight = 0;
        this.cargo = new ArrayList<>();
 
    }
 
    Coordinates move(int x, int y) {
 
        if (this.fuel - (x + y) < 0) {
            throw new IllegalStateException("Unit cannot move that far");
        }
 
        Coordinates coordinatesAfterMove = Coordinates.copy(this.coordinates, x, y);
        this.coordinates = coordinatesAfterMove;
 
        this.fuel = this.fuel - (x + y);
 
        return coordinatesAfterMove;
    }
 
    void tankUp() {
 
        int restPoints = random.nextInt(10);
 
        if (restPoints + this.fuel >= maxFuel) {
            this.fuel = maxFuel;
        } else {
            this.fuel += restPoints;
        }
 
    }
 
    void loadCargo(Cargo cargo) {
 
        if (currentCargoWeight + cargo.getWeight() > maxCargoWeight) {
            throw new IllegalStateException("Can not load any more cargo");
        }
 
        this.cargo.add(cargo);
 
        this.currentCargoWeight = calculateCargoWeight();
 
    }
 
    void unloadCargo(Cargo cargo) {
        this.cargo.remove(cargo);
        this.currentCargoWeight = calculateCargoWeight();
    }
 
    void unloadAllCargo() {
        cargo.clear();
        this.currentCargoWeight = 0;
    }
 
 
    private int calculateCargoWeight() {
        return cargo.stream().mapToInt(Cargo::getWeight).sum();
    }

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		Unit.random = random;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public int getMaxFuel() {
		return maxFuel;
	}

	public void setMaxFuel(int maxFuel) {
		this.maxFuel = maxFuel;
	}

	public List<Cargo> getCargo() {
		return cargo;
	}

	public void setCargo(List<Cargo> cargo) {
		this.cargo = cargo;
	}

	public int getMaxCargoWeight() {
		return maxCargoWeight;
	}

	public void setMaxCargoWeight(int maxCargoWeight) {
		this.maxCargoWeight = maxCargoWeight;
	}

	public int getCurrentCargoWeight() {
		return currentCargoWeight;
	}

	public void setCurrentCargoWeight(int currentCargoWeight) {
		this.currentCargoWeight = currentCargoWeight;
	}
 
}