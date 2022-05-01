package com.example.impostorgame.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.impostorgame.model.Game;
import com.example.impostorgame.model.Match;
import com.example.impostorgame.model.Word;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
	
	@Query("SELECT g FROM Game g WHERE g.gameId = ?1")
	Optional<Game> findGameById(Long gameId);

	@Query("SELECT COUNT(g.matchId) FROM Game g WHERE g.matchId = ?1 and g.gameCompleted = false")
	Optional<Integer> findNotCompletedGames(Long matchId);

	@Query("SELECT m FROM Match m WHERE m.matchId = ?1")
	Optional<Match> getMatch(Long matchId);
	
	@Query("SELECT w.wordId FROM Word w")
	Optional<List<Long>> getWordIdList();
	
	@Query("SELECT w FROM Word w WHERE w.wordId = ?1")
	Optional<Word> getWordById(Long wordId);

	@Query("SELECT mp.playerId FROM MatchPlayer mp WHERE mp.matchId = ?1")
	Optional<List<Long>> getMatchPlayerIdList(Long matchId);

	@Query("SELECT g FROM Game g WHERE g.matchId = ?1")
	Optional<List<Game>> getMatchGamesList(Long matchId);

//	@Query("SELECT w FROM Word w ORDER BY random() LIMIT 1")
//	Optional<Word> getRandomWord();

//	@Query("SELECT m FROM MatchPlayer m WHERE m.matchId = ?1 ORDER BY random() LIMIT 1")
//	Optional<MatchPlayer> getRandomImpostor(Long matchId);
}

