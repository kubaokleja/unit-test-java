package com.kubaokleja.meal;

public class Meal {
	private int price;
	private int quantity;
	private String name;
	
	public Meal() {
		
	}
	
	public Meal(int price) {
		super();
		this.price = price;
	}
	
	public Meal(int price, String name) {
		super();
		this.price = price;
		this.name = name;
	}

	public Meal(int price, int quantity, String name) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}

	public int getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscountedPrice(int discount) {
		if(discount>this.price) {
			throw new IllegalArgumentException("Discount cannot be higher than price");
		}
		return this.price - discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meal [price=" + price + ", name=" + name + "]";
	}
	
}
