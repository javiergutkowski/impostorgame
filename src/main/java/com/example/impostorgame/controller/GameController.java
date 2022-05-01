package com.example.impostorgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.impostorgame.model.Game;
import com.example.impostorgame.service.GameService;
import com.example.impostorgame.service.MatchService;

@RestController
@RequestMapping(path = "api/v1/match/{matchId}")
public class GameController {

	@Autowired
	private final GameService gameService;

	public GameController(GameService gameService) {
		super();
		this.gameService = gameService;
	}

//	@Autowired
//	private final GameService gameService;
//
//	@Autowired
//	private final MatchService matchService;
//
//	public GameController(GameService gameService, MatchService matchService) {
//		super();
//		this.gameService = gameService;
//		this.matchService = matchService;
//	}
	
	@PostMapping(path = "/game")
	public Game createGame(@PathVariable("matchId") Long matchId) {
		//take the match id from the path.
		System.out.println("Game Controller: ");
		return gameService.createNewGame(matchId);
	}

	@PutMapping(path = "/game/{gameId}")
	public Game updateGameComplete(@PathVariable("matchId") Long matchId,
			                       @PathVariable("gameId") Long gameId,
			                       @RequestParam(value = "gameCompleted", required = true) Boolean gameCompleted
			                       ) {
		return gameService.updateGameCompleted(gameId, matchId, gameCompleted);
	}

	@GetMapping(path = "/game/{gameId}")
	public Game getGame(@PathVariable("matchId") Long matchId,
			            @PathVariable("gameId") Long gameId ) {
		return gameService.getGame(matchId, gameId);
	}
	
	@GetMapping(path = "/game")
	public List<Game> getMatchGames(@PathVariable("matchId") Long matchId) {

		return gameService.getMatchGames(matchId);
	}

	@GetMapping(path = "/game/{gameId}/winner")
	public Game getGameWinner(@PathVariable("matchId") Long matchId,
			            @PathVariable("gameId") Long gameId ) {
		return gameService.getGameWinner(matchId, gameId);
	}

}
