package com.example.impostorgame.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.impostorgame.dao.GamePlayerRepository;
import com.example.impostorgame.model.Game;
import com.example.impostorgame.model.GamePlayer;
import com.example.impostorgame.model.MatchPlayer;

@Service
public class GamePlayerService {
	private final GamePlayerRepository gamePlayerRepository;
	
	@Autowired
	public GamePlayerService(GamePlayerRepository gamePlayerRepository) {
		this.gamePlayerRepository = gamePlayerRepository;
	}
	
	public void addGamePlayer(GamePlayer gamePlayer) {
		gamePlayerRepository.save(gamePlayer);
	}

	public List<GamePlayer> getGamePlayers(Long gameId) {
		Optional<List<GamePlayer>> gamePlayers = gamePlayerRepository.getGamePlayersByGameId(gameId);
		if(!gamePlayers.isPresent())
			throw new IllegalStateException("GamePlayer not Found");
		return gamePlayers.get();
	}

	public GamePlayer getSingleGamePlayer(Long gameId, Long playerId) {
		Optional<GamePlayer> gamePlayer = gamePlayerRepository.getGamePlayerById(playerId, gameId);
		if (!gamePlayer.isPresent())
			throw new IllegalStateException("Game Player not found.");
		return gamePlayer.get();
	}

	
	public GamePlayer giveWordHint(Long gameId, Long playerId, String wordHint) {
		GamePlayer gp = getSingleGamePlayer(gameId, playerId) ;
		Integer hintOrder = gamePlayerRepository.getMaxHintOrderByGameId(gameId).orElse(0);
		gp.setHintOrder(hintOrder+1); //increment the max hint order.
		gp.setWordHint(wordHint);
		
		return gamePlayerRepository.save(gp);
	}

	public GamePlayer voteFor(Long gameId, Long playerId, Long voteFor) {
		GamePlayer gp = getSingleGamePlayer(gameId, playerId) ;
		gp.setVoteFor(voteFor);
		
		return gamePlayerRepository.save(gp);
	}

}
