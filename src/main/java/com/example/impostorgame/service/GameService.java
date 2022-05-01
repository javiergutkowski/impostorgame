package com.example.impostorgame.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.impostorgame.dao.GamePlayerRepository;
import com.example.impostorgame.dao.GameRepository;
import com.example.impostorgame.dao.MatchPlayerRepository;
import com.example.impostorgame.model.Game;
import com.example.impostorgame.model.GamePlayer;
import com.example.impostorgame.model.Match;
import com.example.impostorgame.model.MatchPlayer;
import com.example.impostorgame.model.Word;

@Service
public class GameService {

	private final GameRepository gameRepository;
	private final GamePlayerRepository gamePlayerRepository;
//	private final GamePlayerService gamePlayerService;
	private final MatchPlayerRepository matchPlayerRepository;
	//
	@Autowired
	public GameService(GameRepository gameRepository, 
//			           GamePlayerService gamePlayerService,
			           GamePlayerRepository gamePlayerRepository,
			           MatchPlayerRepository matchPlayerRepository
		   ) {
		super();
		this.gameRepository = gameRepository;
//		this.gamePlayerService = gamePlayerService;
		this.matchPlayerRepository = matchPlayerRepository;
		this.gamePlayerRepository = gamePlayerRepository;
	}
	
//	//TO-DO: move out the match queries out of the GameRepository

	public Game createNewGame(Long matchId) {
	
		gameRepository.getMatch(matchId);
		Optional<Match> m = gameRepository.getMatch(matchId);

		//check Match exists.
		if (!m.isPresent()) 
			throw new IllegalStateException("Match " + matchId + " is not Found");
		
		//check that the Match registration is close.
		if (m.get().getRegistrationOpen())
			throw new IllegalStateException("Match " + matchId + " is open for registration. Registration must be closed before starting the Game.");
		
		//check there is no other Game in progress for the match.
		Optional<Integer> notCompletedGamesCounter= gameRepository.findNotCompletedGames(matchId);
		if (notCompletedGamesCounter.get() >0)
			throw new IllegalStateException("Another Game is not yet completed for Match " + matchId + ". All match games must be completed before starting a new game.");

		//choose category and word
		Optional<List<Long>> wordIdList = gameRepository.getWordIdList();
		Long randomWordId = getRandomId(wordIdList);
		Optional<Word> word = gameRepository.getWordById(randomWordId);

		//choose the impostor
		Optional<List<Long>> matchPlayerIdList = gameRepository.getMatchPlayerIdList(matchId);
		Long impostorId = getRandomId(matchPlayerIdList);
		
		Game game = new Game();
		game.setMatchId(matchId);
		game.setCategory(word.get().getCategory());
		game.setWord(word.get().getWord());
		game.setImpostorId(impostorId);
		
		Game gameWithId = gameRepository.save(game);
		//create Game Players
		createGamePlayers(gameWithId);
		
		return gameWithId;
	}

	private void createGamePlayers(Game gameWithId) {
		
		Optional<List<MatchPlayer>> players = matchPlayerRepository.findMatchPlayerByMatchId(gameWithId.getMatchId());
		
		for (MatchPlayer player : players.get()) {
			GamePlayer gamePlayer = new GamePlayer();
			gamePlayer.setPlayerId(player.getPlayerId());
			gamePlayer.setGameId(gameWithId.getGameId());
			gamePlayer.setCategory(gameWithId.getCategory());
			if (gameWithId.getImpostorId() == player.getPlayerId()) {
			  gamePlayer.setIsImpostor(true);
			}else {
				gamePlayer.setIsImpostor(false);
				gamePlayer.setWord(gameWithId.getWord());
			}
			
			gamePlayerRepository.save(gamePlayer)	;
		}
	}

	public Game getGame (Long matchId, Long gameId) {

		Optional<Game> game = gameRepository.findById(gameId);
		
		//check game exists.
		if (!game.isPresent()) 
			throw new IllegalStateException("Game " + gameId + " is not Found");
		
		//check game id is part of this match.
		if (!game.get().getMatchId().equals(matchId))
			throw new IllegalStateException("Game " + gameId + " is part of Match " + matchId +".");
		
		return game.get();
	}

	
	public Game updateGameCompleted (Long gameId, Long matchId, Boolean gameCompleted) {

		Game game = getGame (matchId, gameId);
		game.setGameCompleted(gameCompleted);
		return gameRepository.save(game);
	}
	
	
	public Long getRandomId(Optional<List<Long>> list) {
		Random rand = new Random();
		return list.get().get(rand.nextInt(list.get().size()));
	}

	public List<Game> getMatchGames(Long matchId) {
		return gameRepository.getMatchGamesList(matchId).get();
	}

	public Game getGameWinner(Long matchId, Long gameId) {
		Game game = getGame(matchId, gameId);
		
		if (game.getGameCompleted()) return game;
		
		Optional<List<GamePlayer>> players = gamePlayerRepository.getGamePlayersByGameId(gameId);
		Integer playersQty = 0;
		Integer votesForImpostor = 0;
		for(GamePlayer gp : players.get()) {
			playersQty++;
			if (gp.getVoteFor() == game.getImpostorId())
				votesForImpostor++;
		}
		if (votesForImpostor * 2 > playersQty) {
			game.setImpostorWin(false);
		}else {
			game.setImpostorWin(true);
		}
		game.setGameCompleted(true);
		return gameRepository.save(game);
	}
}
