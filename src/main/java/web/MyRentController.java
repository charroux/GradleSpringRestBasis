package web;

import java.util.ArrayList;
import java.util.List;

import model.Car;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import dto.CarDTO;

@Controller
@Component
public class MyRentController implements RentService{
	
	List<Car> cars = new ArrayList<Car>();
	
	public MyRentController(){
		Car car1 = new Car();
		car1.setPlateNumber("AA11AA");
		car1.setRented(false);
		
		cars.add(car1);
		
		Car car2 = new Car();
		car2.setPlateNumber("BB22BB");
		car2.setRented(false);
		
		cars.add(car2);
	}
	
	@Override
	@RequestMapping(value = "/entryPoint", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResourceSupport get() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	*
	* @return all cars not rented
	*/
	@Override
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CarDTO> getCars() {
		List<CarDTO> dtos = new ArrayList<CarDTO>();
		Car car;
		for(int i=0; i<cars.size(); i++){
			car = cars.get(i);
			if(car.isRented() == false){
				CarDTO carDTO = new CarDTO(car);
				dtos.add(carDTO);
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
			CarDTO dto = new CarDTO(cars.get(i));
			return dto;
		}
		
		throw new Exception("No car with such a plate number");
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
	public void rentCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		
		if(i<cars.size()){
			if(cars.get(i).isRented()){
				throw new Exception("Car already rented");
			} else {
				cars.get(i).setRented(true);
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
	public void renderCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getPlateNumber().equals(plateNumber)==false){
			i++;
		}
		
		if(i<cars.size()){
			if(cars.get(i).isRented() == false){
				throw new Exception("Can not get back a no rented car");
			} else {
				cars.get(i).setRented(false);
			}
		} else {
			throw new Exception("No car with such a plate number");
		}
		
	}

	
}
