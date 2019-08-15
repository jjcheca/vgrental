package com.epg.vgrental.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.Rental;
import com.epg.vgrental.beans.User;
import com.epg.vgrental.repository.RentalRepository;
import com.epg.vgrental.service.RentalService;
 
/**
 * @author Juan José Checa Mora
 *
 */
@Service
public class RentalServiceImpl implements RentalService {
 
	 @Autowired
	 private RentalRepository rentalRepository;
	 
	 public void setRentalRepository(RentalRepository rentalRepository) {
		 
		 this.rentalRepository = rentalRepository;
		 
	 }
	  
	 public List<Rental> retrieveRentals() {
		 
		 List<Rental> rentals = rentalRepository.findAll();
		 
		 return rentals;
		 
	 }
	  
	 public List<Rental> retrieveUserRentals(User user) {
		 
		 Rental notReturnedRental = new Rental();
		 notReturnedRental.setUser(user);
		 
		 Example<Rental> rentalExample = Example.of(notReturnedRental);	
		 
		 List<Rental> rentals = rentalRepository.findAll(rentalExample);
		 
		 return rentals;
		 
	 }
	 
	 public List<Rental> retrieveUnReturnedRentals() {
		 
		 Rental notReturnedRental = new Rental();
		 notReturnedRental.setReturnDate(null);
		 
		 Example<Rental> rentalExample = Example.of(notReturnedRental);
		 
		 List<Rental> rentals = rentalRepository.findAll(rentalExample);
		 
		 return rentals;
		 
	 }
	 
	 public List<Rental> retrieveUnReturnedUserRentals(User user) {
		 
		 Rental notReturnedRental = new Rental();
		 notReturnedRental.setReturnDate(null);
		 notReturnedRental.setUser(user);
		 
		 Example<Rental> rentalExample = Example.of(notReturnedRental);
		 
		 List<Rental> rentals = rentalRepository.findAll(rentalExample);
		 
		 return rentals;
		 
	 }
	 
	 public Rental getRental(Long rentalId) {
		 
		 Optional<Rental> rental = rentalRepository.findById(rentalId);
		 
		 return rental.isPresent() ? rental.get() : null;
		 
	 }
	  
	 public Rental getUserRental(Long rentalId, User user) {
		 
		 Rental notReturnedRental = new Rental();
		 notReturnedRental.setRentalId(rentalId);
		 notReturnedRental.setUser(user);
		 Example<Rental> rentalExample = Example.of(notReturnedRental);
		 
		 Optional<Rental> rental = rentalRepository.findOne(rentalExample);
		 
		 return rental.isPresent() ? rental.get() : null;
		 
	 }	 
	 
	 public Rental saveRental(Rental rental){
		 
		 return rentalRepository.save(rental);
		 
	 }
	  
	 public void deleteRental(Long rentalId){
		 
		 rentalRepository.deleteById(rentalId);
		 
	 }

	@Override
	public Rental getPendingRental(User user, Game game) {
		return rentalRepository.findByUserAndRentedGameAndReturnDateIsNull(user, game);
	}
 
}
