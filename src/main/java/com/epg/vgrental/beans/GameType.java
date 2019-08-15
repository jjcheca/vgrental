package com.epg.vgrental.beans;

public enum GameType {
	NEW_RELEASE(1), STANDARD_GAME(3), CLASSIC_GAME(5);
	private Integer minDays;

	private GameType(Integer minDays) {
		this.minDays = minDays;
	}

	public Integer getMinDays() {
		return minDays;
	}

}
