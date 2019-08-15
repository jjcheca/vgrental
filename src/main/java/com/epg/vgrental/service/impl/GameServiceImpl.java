package com.epg.vgrental.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.repository.GameRepository;
import com.epg.vgrental.service.GameService;
 
/**
 * @author Juan José Checa Mora
 *
 */
@Service
public class GameServiceImpl implements GameService {
 
	 @Autowired
	 protected GameRepository gameRepository;
	 
	 public void setGameRepository(GameRepository gameRepository) {
		 
		 this.gameRepository = gameRepository;
		 
	 }
	  
	 public List<Game> retrieveGames() {
		 
		 List<Game> games = gameRepository.findAll();
		 return games;
		 
	 }
	  
	 public Game getGame(Long gameId) {
		 
		 Optional<Game> game = gameRepository.findById(gameId);
		 
		 return game.isPresent() ? game.get(): null;
		 
	 }
	  
	 public Game saveGame(Game game){
		 
		 return gameRepository.save(game);
		 
	 }
	  
	 public void deleteGame(Long gameId){
		 
		 gameRepository.deleteById(gameId);
		 
	 }
 
}
