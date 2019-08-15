package com.epg.vgrental.repository;


import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.Rental;
import com.epg.vgrental.beans.User;
 
@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {
 
	Collection<Rental> findByReturnDateAndUser(Date returnDate, User user);
	
	Rental findByUserAndRentedGameAndReturnDateIsNull(User user, Game rentedGame);
}
