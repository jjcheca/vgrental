package com.epg.vgrental.controller;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object with one rental") 
public class RentalRequest {

	
	 public RentalRequest() {
		 
	 }
	 
	 @ApiModelProperty(name = "User identifier")
	 @NotNull
	 private Long userId;
	 @ApiModelProperty(name = "Game identifier")
		Long gameId;
		@ApiModelProperty(name = "Number of days")
		Integer numDays;
	 
	 public void setUserId(Long id) {
		 userId = id;
	 }
	 public Long getUserId() {
		 return userId;
	 }
	 public Long getGameId() {
			return gameId;
		}
		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}
		public Integer getNumDays() {
			return numDays;
		}
		public void setNumDays(Integer numDays) {
			this.numDays = numDays;
		}
}