package com.example.impostorgame.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.impostorgame.model.GamePlayer;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long>{

	@Query("SELECT g FROM GamePlayer g WHERE g.playerId = ?1 and g.gameId = ?2")
	Optional<GamePlayer> getGamePlayerById(Long playerId,Long gameId);

	@Query("SELECT g FROM GamePlayer g WHERE g.gameId = ?1")
	Optional<List<GamePlayer>> getGamePlayersByGameId(Long gameId);
	
	@Query("SELECT max (g.hintOrder) FROM GamePlayer g WHERE g.gameId = ?1")
	Optional<Integer> getMaxHintOrderByGameId(Long gameId);

}
