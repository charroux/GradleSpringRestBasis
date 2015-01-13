package web;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

@Controller
@Component
public class MyRentController{

	EntityManager entityManager;	
	List<Car> cars = new ArrayList<Car>();
	
	public MyRentController() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
  		entityManager = emf.createEntityManager();
	}

	@RequestMapping(value = "/car", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Car> getCars() {
		List<Car> cars = entityManager.createQuery("select c from Car c").getResultList();
		return cars; 
	}

	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Car getCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i < cars.size()){
			return cars.get(i);
		}
		throw new Exception("No car: " + plateNumber);
	}

	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void rentCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i < cars.size()){
			cars.get(i).setRented(true);
		}
	}

	@RequestMapping(value = "/car/{plateNumber}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void renderCar(@PathVariable("plateNumber") String plateNumber) throws Exception {
		int i=0;
		while(i<cars.size() && cars.get(i).getNumberPlate().equals(plateNumber)==false){
			i++;
		}
		if(i < cars.size()){
			cars.get(i).setRented(false);
		}
	}

	
}
