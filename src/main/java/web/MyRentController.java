package web;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import dto.CarDTO;


public class MyRentController implements RentService{

	@Override
	public ResourceSupport get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDTO> getCars() {
		// TODO Auto-generated method stub
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
