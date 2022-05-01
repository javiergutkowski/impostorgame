package com.example.impostorgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.impostorgame.model.Match;
import com.example.impostorgame.service.MatchService;

@RestController
@RequestMapping(path = "api/v1/match")
public class MatchController {

	@Autowired
	private final MatchService matchService;
	
	
	public MatchController(MatchService matchService) {
		super();
		this.matchService = matchService;
	}


	@GetMapping
	public List<Match> getMatchs() {

		return matchService.getMatchs();
	}
	
	@PostMapping
	@ResponseBody
	public Match createMatch() {
		
		System.out.println("Controller: New Match with default values");
		return matchService.addNewMatch();
	}	
	
//	@PostMapping
//	public void createMatch(@RequestBody Match match) {
//		
//		matchService.addNewMatch(match);
//		System.out.println("Controller: "+match.toString());
//	}
	
	@PutMapping(path = "{matchId}")
	public void updateMatchRegistrationOpen(@PathVariable("matchId") Long matchId,
			                                @RequestParam(value = "registrationOpen", required = true) Boolean registrationOpen) {
			                             
		System.out.println("Controller updateMatchRegistrationOpen, registrationOpen: " + registrationOpen + ", matchId: "+matchId);
		matchService.updateMatchRegistrationOpen(matchId, registrationOpen);
	}

	@GetMapping(path = "{matchId}")
	public Match getMatch(@PathVariable("matchId") Long matchId) {
		System.out.println("Controller getMatch");
		return matchService.getMatch(matchId);
	}

	
}
