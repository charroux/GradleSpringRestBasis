package web;

import java.util.ArrayList;
import java.util.List;

import model.Car;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
		car1.setPlateNumber("AA 11 AA");
		car1.setRented(true);
		
		cars.add(car1);
		
		Car car2 = new Car();
		car2.setPlateNumber("BB 22 BB");
		car2.setRented(false);
		
		cars.add(car2);
	}
	
	@Override
	public ResourceSupport get() {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public CarDTO getCar(String plateNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rentCar(String plateNumber) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderCar(String plateNumber) throws Exception {
		// TODO Auto-generated method stub
	}

	
}
