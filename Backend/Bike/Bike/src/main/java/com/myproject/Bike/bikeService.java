package com.myproject.Bike;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class bikeService {
	
@Autowired bikerepo bikerepo;

public Bike addBike(Bike bike) {
	bikerepo.save(bike);
	return null;
}

public List<Bike> getallbikesfromdb() {
	
	return bikerepo.findAll();
	
	
}

public Optional<Bike> getBikeById(int id) {
	return bikerepo.findById(id);
}

public boolean deleteBike(int id) {
	try {
	    bikerepo.deleteById(id);
	    return true;
	} catch (EmptyResultDataAccessException e) {
	    return false;
	}
}

public Bike updateBike(Bike existingBike) {
	
	return bikerepo.save(existingBike);
}





	
	
	

}
