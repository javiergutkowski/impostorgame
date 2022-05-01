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
public class Match implements Serializable{
	@Id
	@SequenceGenerator(
			name = "match_sequence",
			sequenceName = "match_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "match_sequence"
	)
	
	private Long matchId;
	private Boolean registrationOpen;
	
	public Match(Long matchId, Boolean registrationOpen) {
		super();
		this.matchId = matchId;
		this.registrationOpen = registrationOpen;
	}

	public Match(Boolean registrationOpen) {
		super();
		this.registrationOpen = registrationOpen;
	}

	public Match() {
		super();
		this.registrationOpen = true;
	}

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", registrationOpen=" + registrationOpen + "]";
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Boolean getRegistrationOpen() {
		return registrationOpen;
	}

	public void setRegistrationOpen(Boolean registrationOpen) {
		this.registrationOpen = registrationOpen;
	}

	
	
}
