package com.example.impostorgame.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.impostorgame.model.Match;
import com.example.impostorgame.model.MatchPlayer;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {
	
	@Query("SELECT m FROM MatchPlayer m WHERE m.matchId = ?1 and m.playerId = ?2")
	Optional<MatchPlayer> findMatchPlayerById(Long matchId, Long matchPlayerId);

	@Query("SELECT m FROM MatchPlayer m WHERE m.matchId = ?1")
	Optional<List<MatchPlayer>> findMatchPlayerByMatchId(Long matchId);

	@Query("SELECT m FROM MatchPlayer m WHERE m.matchId = ?1 and m.playerName = ?2")
	Optional<MatchPlayer> checkNameIsTaken(Long matchId, String playerName);

	@Query("SELECT m FROM Match m WHERE m.matchId = ?1")
	Optional<Match> getMatch(Long matchId);
}
