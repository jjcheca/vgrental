package com.epg.vgrental.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Juan José Checa Mora
 *
*/
@Entity
@Table(name="GAME")
public class Game {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="GAME_ID")
	private Long gameId;
	
	@Column(name="GAME_NAME")		
	private String gameName;
	
	@Enumerated(EnumType.STRING)	
	@Column(name="GAME_TYPE")		
	private GameType gameType;
	
//	@Column(name="GAME_PRICE")		
//	private PriceType gamePrice;

	public Game() {		
		super();
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

//	public PriceType getGamePrice() {
//		return gamePrice;
//	}
//
//	public void setGamePrice(PriceType gamePrice) {
//		this.gamePrice = gamePrice;
//	}

}
