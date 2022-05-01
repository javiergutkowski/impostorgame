# impostorgame
**Entity 1:
Match (matchId, registrationOpen)**
APIs
getMatchs: get a List of all the matches.
addNewMatch: create a new match
getMatch: get a single match.
updateMatchRegistrationOpen: Update open registration flag (true/false).

**Entity 2:
MatchPlayer (matchId, playerId, playerName)**
APIs
getMatchPlayers: get a List of all the match players.
createMatchPlayer: create a new match player. 
Checks: Check the match id exists, 
the match registration is open, 
the player’s name is not empty, 
the player’s name is not taken
updateMatchPlayerName: get a single match player.

**Entity 3:
Game (gameId, matchId, category, word, impostorId, gameCompleted, impostorWin)**
APIs
getMatchGames: get a List of all the match Games.
createNewGame: create a new game.
		Check match exists and the registration is closed.
		Check there no other open game for that match.
		Select random category and word.
		Select random impostor.
		Should create the Game Players.
getGame: get a single game.
updateGameCompleted: Update Game Completed flag (true/false).
Winner: count the votes and set if the impostor win or loose.

**Entity 4:
GamePlayer(gameId, playerid, word, category, impostorInd, wordHint, hintOrder, voteFor)**
APIs
getGamePlayers: get a List of all the match Games.
getSingleGamePlayer: get a single game player.
giveWordHint
vote




El impostor pierde solo cuando hay **mayoría absoluta** en su contra. (mayoría absoluta es que mas del 50% de los jugadores votan al impostor).
Si el impostor es A, el impostor gana en estos casos:
Distribucion de votos:
•	3A, 3B
•	3A, 2B, 1C
Cuando hay 6 jugadores, 4 deben votar contra el impostor para que este pierda.


**Road Map**  To improve:
1.	Vote to player name, instead of player id.
2.	Add additional checks for the case of not valid ids (match/player/game)
3.	Unify logic (@Transactional vs Repository.save)
4.	Better Get votes.  get gamePlayers
