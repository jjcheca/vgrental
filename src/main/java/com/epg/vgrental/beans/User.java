package com.epg.vgrental.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
/**
 * @author Juan José Checa Mora
 *
*/
@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;

	@Column(name="USER_NICK")	
	private String userNick;

	@Column(name="USER_NAME")		
	private String userName;

	@Column(name="USER_ADDRESS")		
	private String userAddress;

	@Column(name="USER_LOYALTIES")		
	private Integer userLoyalties;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserLoyalties() {
		return userLoyalties;
	}

	public void setUserLoyalties(Integer userLoyalties) {
		this.userLoyalties = userLoyalties;
	}


}
