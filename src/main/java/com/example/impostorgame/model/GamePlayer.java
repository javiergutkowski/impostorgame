package com.example.impostorgame.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table 
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@IdClass(GamePlayerId.class)
public class GamePlayer implements Serializable {
	@Id
	private Long playerId;
	@Id
	private Long gameId;
	private String word;
	private String category;
	private Boolean isImpostor;
	private String wordHint;
	private Integer hintOrder;
	private Long voteFor;
	
	public GamePlayer(Long playerId, Long gameId, String word, String category, Boolean isImpostor, String wordHint,
			Integer hintOrder, Long voteFor) {
		super();
		this.playerId = playerId;
		this.gameId = gameId;
		this.word = word;
		this.category = category;
		this.isImpostor = isImpostor;
		this.wordHint = wordHint;
		this.hintOrder = hintOrder;
		this.voteFor = voteFor;
	}

	public GamePlayer(Long playerId, Long gameId, String word, String category, Boolean isImpostor) {
		super();
		this.playerId = playerId;
		this.gameId = gameId;
		this.word = word;
		this.category = category;
		this.isImpostor = isImpostor;
	}

	
	public GamePlayer() {
		super();
	}

	@Override
	public String toString() {
		return "GamePlayer [playerId=" + playerId + ", gameId=" + gameId + ", word=" + word + ", category=" + category
				+ ", isImpostor=" + isImpostor + ", wordHint=" + wordHint + ", hintOrder=" + hintOrder + ", voteFor="
				+ voteFor + "]";
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getIsImpostor() {
		return isImpostor;
	}

	public void setIsImpostor(Boolean isImpostor) {
		this.isImpostor = isImpostor;
	}

	public String getWordHint() {
		return wordHint;
	}

	public void setWordHint(String wordHint) {
		this.wordHint = wordHint;
	}

	public Integer getHintOrder() {
		return hintOrder;
	}

	public void setHintOrder(Integer hintOrder) {
		this.hintOrder = hintOrder;
	}

	public Long getVoteFor() {
		return voteFor;
	}

	public void setVoteFor(Long voteFor) {
		this.voteFor = voteFor;
	}
	

	

}
