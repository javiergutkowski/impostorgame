package com.example.impostorgame.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.impostorgame.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	
	@Query("SELECT m FROM Match m WHERE m.matchId = ?1")
	Optional<Match> findMatchById(Long matchId);
	
}
