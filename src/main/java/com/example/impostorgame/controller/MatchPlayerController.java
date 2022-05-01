package com.example.impostorgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.impostorgame.model.MatchPlayer;
import com.example.impostorgame.service.MatchPlayerService;

@RestController
@RequestMapping(path = "api/v1/match/{matchId}")
public class MatchPlayerController {

	@Autowired
	private final MatchPlayerService matchPlayerService;
	
	
	public MatchPlayerController(MatchPlayerService matchPlayerService) {
		super();
		this.matchPlayerService = matchPlayerService;
	}


	@GetMapping(path = "/matchPlayer")
	public List<MatchPlayer> getMatchPlayers(@PathVariable("matchId") Long matchId) {

		return matchPlayerService.getMatchPlayers(matchId);
	}
	
	
	
//	@PostMapping(path = "/matchPlayer")
//	public void createMatchPlayer(@RequestBody MatchPlayer matchPlayer) {
//		
//		matchPlayerService.addNewMatchPlayer(matchPlayer);
//		System.out.println("Controller: "+matchPlayer.toString());
//	}
	
	@PostMapping(path = "/matchPlayer")
	public MatchPlayer createMatchPlayer(@PathVariable("matchId") Long matchId,
			                             @RequestBody MatchPlayer matchPlayer) {
		//take the match id from the path.
		matchPlayer.setMatchId(matchId);
		System.out.println("Controller: "+matchPlayer.toString());
		return matchPlayerService.addNewMatchPlayer(matchPlayer);
	}
	
	@PutMapping(path = "/matchPlayer/{matchPlayerId}")
	public void updateMatchPlayerName(@PathVariable("matchId") Long matchId,
									  @PathVariable("matchPlayerId") Long matchPlayerId,
			                          @RequestParam(value = "playerName", required = true) String playerName) {
			                             
		System.out.println("Controller updateMatchPlayerName, playerName: " + playerName + ", matchPlayerId: "+matchPlayerId);
		matchPlayerService.updateMatchPlayerName(matchId, matchPlayerId, playerName);
	}

	@GetMapping(path = "/matchPlayer/{matchPlayerId}")
	public MatchPlayer getMatchPlayer(@PathVariable("matchId") Long matchId, 
									  @PathVariable("matchPlayerId") Long matchPlayerId) {
		System.out.println("Controller getMatchPlayer");
		return matchPlayerService.getMatchPlayer(matchId, matchPlayerId);
	}
	
}
