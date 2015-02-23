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
		return null;		
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
