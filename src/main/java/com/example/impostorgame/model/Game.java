package com.example.impostorgame.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table 
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game implements Serializable{

	@Id
	@SequenceGenerator(
			name = "game_sequence",
			sequenceName = "game_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "game_sequence"
	)
	private Long gameId;
	private Long matchId;
	private String category;
	private String word;
	private Long impostorId;
	private Boolean gameCompleted;
	private Boolean impostorWin; 
	
	
	public Game(Long gameId, Long matchId, String category, String word, Long impostorId, Boolean gameCompleted, Boolean impostorWin) {
		super();
		this.gameId = gameId;
		this.matchId = matchId;
		this.category = category;
		this.word = word;
		this.impostorId = impostorId;
		this.gameCompleted = gameCompleted;
		this.impostorWin = impostorWin;
	}
	
	public Game(Long matchId, String category, String word, Long impostorId, Boolean gameCompleted, Boolean impostorWin) {
		super();
		this.matchId = matchId;
		this.category = category;
		this.word = word;
		this.impostorId = impostorId;
		this.gameCompleted = gameCompleted;
		this.impostorWin = impostorWin;
	}

	public Game() {
		super();
		this.gameCompleted = false;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Long getImpostorId() {
		return impostorId;
	}

	public void setImpostorId(Long impostorId) {
		this.impostorId = impostorId;
	}

	public Boolean getGameCompleted() {
		return gameCompleted;
	}

	public void setGameCompleted(Boolean gameCompleted) {
		this.gameCompleted = gameCompleted;
	}

	
	public Boolean getImpostorWin() {
		return impostorWin;
	}

	public void setImpostorWin(Boolean impostorWin) {
		this.impostorWin = impostorWin;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", matchId=" + matchId + ", category=" + category + ", word=" + word
				+ ", impostorId=" + impostorId + ", gameCompleted=" + gameCompleted + ", impostorWin=" + impostorWin
				+ "]";
	}



	
}
