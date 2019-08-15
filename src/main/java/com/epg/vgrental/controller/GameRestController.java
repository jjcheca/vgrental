package com.epg.vgrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.service.GameService;

/**
 * @author Juan José Checa Mora
 *
 */
@RestController
public class GameRestController {

	@Autowired
	private GameService gameService;

	public void setGameService(GameService gameService) {

		this.gameService = gameService;

	}

	@GetMapping("/api/games")
	public List<Game> getGames() {

		List<Game> games = gameService.retrieveGames();

		return games;

	}

	@GetMapping("/api/games/{gameId}")
	public Game getGame(@PathVariable(name = "gameId") Long gameId) {

		return gameService.getGame(gameId);

	}

	@PostMapping("/api/games")
	public void saveGame(@RequestBody Game game) {

		gameService.saveGame(game);
		System.out.println("Game Saved Successfully");

	}

	@DeleteMapping("/api/games/{gameId}")
	public void deleteGame(@PathVariable(name = "gameId") Long gameId) {

		gameService.deleteGame(gameId);
		System.out.println("Game Deleted Successfully");

	}

	@PutMapping("/api/games/{gameId}")
	public Game updateGame(@RequestBody Game game, @PathVariable(name = "gameId") Long gameId) {
		Game emp = gameService.getGame(gameId);
		if (emp != null) {
			emp.setGameType(game.getGameType());
			emp.setGameName(game.getGameName());
			return gameService.saveGame(emp);
		}
		return null;
	}

}
