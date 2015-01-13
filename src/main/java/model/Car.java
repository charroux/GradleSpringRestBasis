package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
	
	String numberPlate;
	boolean rented;
	
	@Id
	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
