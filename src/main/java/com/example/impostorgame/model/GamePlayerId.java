package com.example.impostorgame.model;

import java.io.Serializable;

public class GamePlayerId implements Serializable{

	private Long gameId;
	private Long playerId;
	
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	

}
