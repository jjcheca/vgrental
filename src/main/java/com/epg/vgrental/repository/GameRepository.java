package com.epg.vgrental.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.GameType;
 
@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
	
	Collection<Game> findByGameType(GameType type);
}
