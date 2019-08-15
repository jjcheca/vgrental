package com.epg.vgrental.service;
 
import java.util.List;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.Rental;
import com.epg.vgrental.beans.User;	 
	 
/**
 * @author Juan José Checa Mora
 *
 */
public interface RentalService {
	
	 List<Rental> retrieveRentals();
	 
	  List<Rental> retrieveUserRentals(User user);
	 
	  List<Rental> retrieveUnReturnedRentals();
	 
	  List<Rental> retrieveUnReturnedUserRentals(User user);
	  
	  Rental getRental(Long rentalId);
	 
	  Rental getUserRental(Long rentalId, User user);
	  
	  Rental saveRental(Rental rental);
	  
	  void deleteRental(Long rentalId);
	 
	  Rental getPendingRental(User user, Game game);
	 
	 
}
