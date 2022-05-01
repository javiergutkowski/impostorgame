package com.example.impostorgame.model;

import java.io.Serializable;

public class MatchPlayerId implements Serializable{
	private Long matchId;
	private Long playerId;
	
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

}
