package web;

import java.util.ArrayList;
import java.util.List;

import model.Car;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import services.CarsManager;
import dto.CarDTO;

@Controller
@Component
public class MyRentController implements RentService{

	@Override
	@RequestMapping(value = "/entryPoint", method = RequestMethod.GET) 
	@ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public ResourceSupport get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/car", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CarDTO> getCars() {
		List<Car> cars = CarsManager.getCars();
		List<CarDTO> carDTOs = new ArrayList<CarDTO>();
		int i=0;
		while(i<cars.size()){
			CarDTO dto = new CarDTO();
			dto.setNumberPlate(cars.get(i).getNumberPlate());
			carDTOs.add(dto);
			i++;
		}
		return carDTOs; 
	}

	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@Override
	public CarDTO getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		List<Car> cars = CarsManager.getCars();
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i == cars.size()){
			throw new Exception("No car with " + plateNumber);
		}
		CarDTO dto = new CarDTO();
		dto.setNumberPlate(cars.get(i).getNumberPlate());
		return dto;
	}

	@Override
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void rentCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		List<Car> cars = CarsManager.getCars();
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i == cars.size()){
			throw new Exception("No car with " + plateNumber);
		}
		cars.get(i).setRented(true);
	}

	@Override
	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void renderCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		List<Car> cars = CarsManager.getCars();
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i == cars.size()){
			throw new Exception("No car with " + plateNumber);
		}
		cars.get(i).setRented(false);
		
	}

	
}
