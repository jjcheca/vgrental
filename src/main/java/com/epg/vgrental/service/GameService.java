package com.epg.vgrental.service;
 
import java.util.List;

import com.epg.vgrental.beans.Game;	 
	 
/**
 * @author Juan José Checa Mora
 *
 */
public interface GameService {
	
	 List<Game> retrieveGames();
	  
	 Game getGame(Long gameId);
	  
	 Game saveGame(Game game);
	  
	 void deleteGame(Long gameId);
}
