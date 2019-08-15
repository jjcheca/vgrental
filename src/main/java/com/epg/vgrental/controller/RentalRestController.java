package com.epg.vgrental.controller;

import java.security.InvalidParameterException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.GameType;
import com.epg.vgrental.beans.Rental;
import com.epg.vgrental.beans.User;
import com.epg.vgrental.service.GameService;
import com.epg.vgrental.service.RentalService;
import com.epg.vgrental.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * @author Juan José Checa Mora
 *
 */
@RestController
@Api(tags = { "Rental Controller" })
public class RentalRestController {

	@Value("${epg.vgrental.price.basic:3}")
	Long BASIC_PRICE;
	@Value("${epg.vgrental.price.premium:4}")
	Long PREMIUM_PRICE;

	@Autowired
	private RentalService rentalService;

	@Autowired
	private UserService userService;

	@Autowired
	private GameService gameService;

	public RentalService getRentalService() {
		return rentalService;
	}

	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("/api/rentals")
	public List<Rental> getRentals() {

		List<Rental> rentals = rentalService.retrieveRentals();

		return rentals;

	}

	@GetMapping("/api/rentals/{userId}")
	public List<Rental> getUserRentals(@PathVariable(name = "userId") Long userId) {

		User user = userService.getUser(userId);
		List<Rental> rentals = new ArrayList<Rental>();

		if (user != null) {
			rentals = rentalService.retrieveUserRentals(user);
		} else {
			System.out.println("User not exists.");
		}
		return rentals;

	}

	@GetMapping("/api/rentals/unreturned")
	public List<Rental> getUnReturnedRentals() {

		List<Rental> rentals = rentalService.retrieveUnReturnedRentals();

		return rentals;

	}

	@GetMapping("/api/rentals/unreturned/{userId}")
	public List<Rental> getUnReturnedUserRentals(@PathVariable(name = "userId") Long userId) {

		User user = userService.getUser(userId);
		List<Rental> rentals = new ArrayList<Rental>();

		if (user != null) {
			rentals = rentalService.retrieveUnReturnedUserRentals(user);
		} else {
			System.out.println("User not exists.");
		}

		return rentals;

	}

	@GetMapping("/api/rental/{rentalId}")
	public Rental getRental(@PathVariable(name = "rentalId") Long rentalId) {

		return rentalService.getRental(rentalId);

	}

	@PostMapping("/api/rental")
	public Rental saveRental(@RequestBody Rental rental) {
		try {
			return rentalService.saveRental(rental);
		} finally {
			System.out.println("Rental Saved Successfully");
		}

	}

	@ApiModel(description = "Object with rental result")
	class RentalResponse {
		@ApiModelProperty(name = "Collection of rentals")
		private Collection<Rental> rentals;
		@ApiModelProperty(name = "Total price")
		private Long price;

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public Collection<Rental> getRentals() {
			if (rentals == null) {
				rentals = new ArrayList<>();
			}
			return rentals;
		}
	}

	private Long calculatePrice(GameType gameType, Integer numDays) {
		Long result;
		Long price;
		switch (gameType) {
		case NEW_RELEASE:
			price = PREMIUM_PRICE;
			break;
		case STANDARD_GAME:
		case CLASSIC_GAME:
			price = BASIC_PRICE;
			break;
		default:
			throw new InvalidParameterException();
		}
		if (numDays > gameType.getMinDays()) {
			result = price.longValue() * (numDays - gameType.getMinDays() + 1);
		} else {
			result = price.longValue();
		}
		return result;
	}

	@ApiOperation(value = "Rental games", nickname = "rentalGames")
	@RequestMapping(method = RequestMethod.POST, path = "/api/rentals/{userId}")
	public RentalResponse rentalGames(@PathVariable("userId") Long userId,
			@Valid @RequestBody Collection<RentalRequest> request) {

		RentalResponse result = new RentalResponse();
		result.setPrice(0L);
		User user = userService.getUser(userId);
		if (user != null) {
			request.forEach((rental) -> {
				Game game = gameService.getGame(rental.getGameId());
				if (game != null) {

					Rental gameRental = new Rental();
					gameRental.setRentalDays(rental.getNumDays());
					gameRental.setRentedGame(game);
					gameRental.setRentalPrice(calculatePrice(game.getGameType(), rental.getNumDays()));
					gameRental.setUser(user);
					gameRental.setCheckoutDate(new Date());
					gameRental.setReturnDate(null);
					result.setPrice(result.getPrice() + gameRental.getRentalPrice());
					result.getRentals().add(rentalService.saveRental(gameRental));
				}
			});
		}

		System.out.println("Rental Saved Successfully");

		return result;

	}

	@DeleteMapping("/api/rental/{rentalId}")
	public void deleteRental(@PathVariable(name = "rentalId") Long rentalId) {
		rentalService.deleteRental(rentalId);
		System.out.println("Rental Deleted Successfully");
	}

	@ApiModel(description = "Object with rental result")
	class ReturnResponse {
		@ApiModelProperty(name = "Rental")
		private Rental rental;
		@ApiModelProperty(name = "Total price")
		private Long price;

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public Rental getRental() {
			return rental;
		}

		public void setRental(Rental rental) {
			this.rental = rental;
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/api/rental/{userId}/return/{gameId}")
	public ReturnResponse returnRental(@PathVariable("userId") Long userId, @PathVariable("gameId") Long gameId) {
		ReturnResponse result = new ReturnResponse();
		User user = userService.getUser(userId);
		Game game = gameService.getGame(gameId);
		if (user != null && game != null) {
			Rental rental = rentalService.getPendingRental(user, game);
			if (rental != null) {
				Date now = new Date();
				long diffInMillies = rental.getCheckoutDate().getTime() - now.getTime();
				long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				result.setPrice(0L);
				if(diffDays > rental.getRentalDays()) {
					result.setPrice(calculatePrice(game.getGameType(), 0)* (diffDays - rental.getRentalDays()));
				}
				rental.setReturnDate(now);
				Rental updated = rentalService.saveRental(rental);
				result.setRental(updated);
			}
		}
		return result;
	}

}
