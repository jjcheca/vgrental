package com.epg.vgrental.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Juan José Checa Mora
 *
*/
@Entity
@Table(name="RENTAL")
@ApiModel(value = "Rental properties")
public class Rental {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="RENTAL_ID", nullable = false)
	@ApiModelProperty(name = "Rental identifier")
	private Long rentalId;
	
    @JoinColumn(name = "RENTED_GAME", referencedColumnName = "GAME_ID", nullable = false)
    @ManyToOne(optional = false)
	private Game rentedGame;
	
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(optional = false)	
	private User user;
	
	@Column(name="RENTAL_PRICE")    
	private Long rentalPrice;

	@Column(name="RENTAL_DAYS")    
	private Integer rentalDays;
	
//	@Column(name="RENTAL_POINT")	
//	private Integer loyaltyPoints;
	
//	@Column(name="rentalStatus")		
//	private Boolean rentalStatus;
	
    @Column(name = "RETURN_DATE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;    

    @Column(name = "CHECKOUT_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDate;

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public void setRentedGame(Game rentedGame) {
		this.rentedGame = rentedGame;
	}

	public Long getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(Long rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	
//	public Integer getLoyaltyPoints() {
//		return loyaltyPoints;
//	}
//
//	public void setLoyaltyPoints(Integer loyaltyPoints) {
//		this.loyaltyPoints = loyaltyPoints;
//	}

	public Integer getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(Integer rentalDays) {
		this.rentalDays = rentalDays;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Game getRentedGame() {
		return rentedGame;
	}

//	public Boolean getRentalStatus() {
//		return rentalStatus;
//	}
//
//	public void setRentalStatus(Boolean rentalStatus) {
//		this.rentalStatus = rentalStatus;
//	}

//	public void calculateRentalPrice() {
//		
//		this.rentalPrice = this.rentedGames.stream().mapToInt(game -> game.getPrice()).sum();
//	
//	}
	
//	public void calculateloyaltyPoints() {
//		
//		this.rentalPrice = this.rentedGames.stream().mapToInt(game -> game.getLoyaltyPoints()).sum();
//	
//	}	
	
	
}
