package com.example.impostorgame.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.impostorgame.dao.MatchPlayerRepository;
import com.example.impostorgame.dao.MatchRepository;
import com.example.impostorgame.model.Match;
import com.example.impostorgame.model.MatchPlayer;

@Service
public class MatchPlayerService {

	private final MatchPlayerRepository matchPlayerRepository;
	
	@Autowired
	public MatchPlayerService(MatchPlayerRepository matchPlayerRepository) {
		super();
		this.matchPlayerRepository = matchPlayerRepository;
	}

	public List<MatchPlayer> getMatchPlayers(Long matchId) {
		System.out.println("Service getMatchPlayers");
		Optional<List<MatchPlayer>> matchPlayers = matchPlayerRepository.findMatchPlayerByMatchId(matchId);
		if (!matchPlayers.isPresent()) {
			throw new IllegalStateException("MatchPlayer not Found");
		}
		return matchPlayers.get();
	}
	
	public MatchPlayer addNewMatchPlayer(MatchPlayer matchPlayer) {
		System.out.println(matchPlayer);
		//Check whether match id exists
		Match match = matchPlayerRepository.getMatch(matchPlayer.getMatchId())
				                           .orElseThrow(() -> new IllegalStateException("Match with id "+matchPlayer.getMatchId() +" does not exist"));
		//Check registration is open.
		if (!match.getRegistrationOpen()) 
			throw new IllegalStateException("Registration is closed for Match with id "+matchPlayer.getMatchId());
		
		checkPlayerNameIsNotNull(matchPlayer.getPlayerName() );
		checkNameIsNotTaken(matchPlayer.getMatchId(), matchPlayer.getPlayerName());	
		
		return matchPlayerRepository.save(matchPlayer);
	}
	
	//Check name is not null.
	public void checkPlayerNameIsNotNull (String playerName) {
		if (playerName == null || playerName.length() == 0) 
			throw new IllegalStateException("Must provide a player name. Player Name cannot be empty.");		
	}
	
	//Check name is not taken.
	public void checkNameIsNotTaken(Long matchId, String playerName) {
		Optional<MatchPlayer> mp = matchPlayerRepository.checkNameIsTaken(matchId, playerName);
		if(mp.isPresent())
			throw new IllegalStateException("Name "+ playerName + " is already taken  for Match with id "+matchId);
		
	}
	
	public MatchPlayer getMatchPlayer(Long matchId, Long matchPlayerId) {
		System.out.println("Service getMatchPlayer");
		Optional<MatchPlayer> matchPlayer = matchPlayerRepository.findMatchPlayerById(matchId, matchPlayerId);
		if (!matchPlayer.isPresent()) {
			throw new IllegalStateException("MatchPlayer not Found");
		}
		return matchPlayer.get();
	}

    @Transactional
	public void updateMatchPlayerName(Long matchId, Long matchPlayerId,String playerName) {
    	System.out.println("updateMatchPlayerName");
		MatchPlayer matchPlayer = matchPlayerRepository.findMatchPlayerById(matchId, matchPlayerId)
				      .orElseThrow(()-> new IllegalStateException("MatchPlayer Id: "+matchPlayerId+"does not exist."));

		checkPlayerNameIsNotNull(playerName);
		checkNameIsNotTaken(matchId, playerName);		

		matchPlayer.setPlayerName(playerName);
		
	}
}
