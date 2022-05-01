package com.example.impostorgame.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	

@Entity
@Table 
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@IdClass(MatchPlayerId.class)
public class MatchPlayer implements Serializable{
	@Id
	private Long matchId;

	@Id
	@SequenceGenerator(
			name = "player_sequence",
			sequenceName = "player_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "player_sequence"
	)

	private Long playerId;
	private String playerName;
	
	public MatchPlayer(Long matchId, Long playerId, String playerName) {
		super();
		this.matchId = matchId;
		this.playerId = playerId;
		this.playerName = playerName;
	}

	public MatchPlayer(Long matchId, String playerName) {
		super();
		this.matchId = matchId;
		this.playerName = playerName;
	}

	public MatchPlayer() {
		super();
	}

	
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return "MatchPlayer [matchId=" + matchId + ", playerId=" + playerId + ", playerName=" + playerName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchId == null) ? 0 : matchId.hashCode());
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchPlayer other = (MatchPlayer) obj;
		if (matchId == null) {
			if (other.matchId != null)
				return false;
		} else if (!matchId.equals(other.matchId))
			return false;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}

	
}