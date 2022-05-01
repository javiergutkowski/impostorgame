package com.example.impostorgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.impostorgame.model.GamePlayer;
import com.example.impostorgame.service.GamePlayerService;

@RestController
@RequestMapping(path = "api/v1/match/{matchId}/game/{gameId}")
public class GamePlayerController {

	@Autowired
	private final GamePlayerService gamePlayerService;

	public GamePlayerController(GamePlayerService gamePlayerService) {
		super();
		this.gamePlayerService = gamePlayerService;
	}

	@GetMapping(path = "/gamePlayer")
	public List<GamePlayer> getGamePlayers(@PathVariable ("gameId") Long gameId){
		return gamePlayerService.getGamePlayers(gameId);
	}
	
	@GetMapping(path = "/gamePlayer/{playerId}")
	public GamePlayer getSingleGamePlayer(@PathVariable ("gameId") Long gameId,
			                              @PathVariable("playerId") Long playerId){
		return gamePlayerService.getSingleGamePlayer(gameId, playerId);
	}

	@PutMapping(path = "/gamePlayer/{playerId}")
	public GamePlayer giveHintWord(@PathVariable("gameId") Long gameId,
            					   @PathVariable("playerId") Long playerId,
            					   @RequestParam(value = "wordHint", required = false) String wordHint,
            					   @RequestParam(value = "voteFor", required = false) Long voteFor){
		GamePlayer gamePlayer = null; 
		if (wordHint != null) {
			gamePlayer = gamePlayerService.giveWordHint(gameId, playerId,wordHint);
		}else if (voteFor != null) {
			gamePlayer = gamePlayerService.voteFor(gameId, playerId, voteFor);
		}
		return gamePlayer;
	}
	
}
