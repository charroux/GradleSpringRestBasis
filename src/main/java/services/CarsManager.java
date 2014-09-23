package services;

import java.util.ArrayList;
import java.util.List;

import model.Car;

public class CarsManager {
	
	static List<Car> cars;
	
	static{
		cars = new ArrayList<Car>();
		
		Car c1 = new Car();
		c1.setNumberPlate("345 FFF 455");
		cars.add(c1);
		
		Car c2 = new Car();
		c2.setNumberPlate("888 CCC 888");
		cars.add(c2);
	}
		
	
	public static List<Car> getCars(){
		return cars;
	}

}
