package web;

import java.util.ArrayList;
import java.util.List;

import model.Car;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dto.CarDTO;

@Controller
public class MyRentController implements RentService{

	List<Car> cars;
	
	public MyRentController(){
		cars = new ArrayList<Car>();
		Car car = new Car();
		car.setPlateNumber("11AA11");
		car.setRented(false);
		cars.add(car);
		car = new Car();
		car.setPlateNumber("22NN22");
		car.setRented(false);
		cars.add(car);
	}
	
	@RequestMapping(value = "/entryPoint", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public ResourceSupport get() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	*
	* @return all cars not rented
	*/
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public List<CarDTO> getCars() {
		List<CarDTO> dtos = new ArrayList<CarDTO>();
		Car car;
		for(int i=0; i<cars.size(); i++){
			car = cars.get(i);
			if(car.isRented() == false){
				dtos.add( new CarDTO(car));
			}
		}
		return dtos;
	}

	/**
	* Return specifications of a car.
	* @param plateNumber
	* @return car specifications only (if not rented)
	* @throws Exception no car with this plate number or already rented
	*/
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public CarDTO getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		if(i<cars.size()){
			if(cars.get(i).isRented() == false){
				return new CarDTO(cars.get(i));
			} else {
				throw new Exception("Car is already rented");
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
	}

	/**
	* Rent a car.
	* @param plateNumber
	* @return car specifications
	* @throws Exception no car with this plate number or already rented
	*/
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@Override
	public void rentCar(String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		if(i<cars.size()){
			if(cars.get(i).isRented() == false){
				cars.get(i).setRented(true);
			} else {
				throw new Exception("Car is already rented");
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
	}

	/**
	*
	** @return actions to be done
	* @throws Exception no car with this plate number or not rented
	*/
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@Override
	public void renderCar(String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		if(i<cars.size()){
			if(cars.get(i).isRented() == true){
				cars.get(i).setRented(false);
			} else {
				throw new Exception("Car is not rented");
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
	}

}
