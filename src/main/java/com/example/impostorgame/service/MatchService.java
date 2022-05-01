package com.example.impostorgame.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.impostorgame.dao.MatchRepository;
import com.example.impostorgame.model.Match;

@Service
public class MatchService {

	private final MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		super();
		this.matchRepository = matchRepository;
	}


	public List<Match> getMatchs(){
		return matchRepository.findAll();

	}
	
	public void addNewMatch(Match match) {
		System.out.println(match);
		matchRepository.save(match);
	}
	
	public Match addNewMatch() {
		System.out.println("Create Match with default values");
		Match match = new Match();
		matchRepository.save(match);
		return match;
	}
	
	public Match getMatch(Long matchId) {
		System.out.println("Service getMatch");
		Boolean matchExists = matchRepository.existsById(matchId);
		if (!matchExists) {
			throw new IllegalStateException("Match not Found");
		}
		return matchRepository.getById(matchId);
	}
	
//	public Boolean checkMatchOpenForRegistration(Match match) {
//		System.out.println(match);
//		Optional<Match> m= matchRepository.findMatchById(match.getMatchId());
//		if (!m.isPresent()) {
//			throw new IllegalStateException("Match not Found");
//		}
//		return m.get().getRegistrationOpen();
//	}

    @Transactional
	public void updateMatchRegistrationOpen(Long matchId, Boolean registrationOpen) {
    	System.out.println("updateMatchRegistrationOpen");
		Match match = matchRepository.findById(matchId)
				      .orElseThrow(()-> new IllegalStateException("Match Id: "+matchId+"does not exist."));
		match.setRegistrationOpen(registrationOpen);
		
	}
}
