package com.epg.vgrental.service;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.epg.vgrental.beans.Game;
import com.epg.vgrental.beans.GameType;
import com.epg.vgrental.repository.GameRepository;
import com.epg.vgrental.service.impl.GameServiceImpl;

@RunWith(SpringRunner.class)
public class GameServiceTest {
 
	GameServiceImpl service;
	
	@MockBean
	GameRepository repository;
	
	public GameServiceTest() {
		
	}
	
	@BeforeClass
    public static void setUpClass() {
       
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Before
    public void setUp()  {
    	service = new GameServiceImpl();
    	service.setGameRepository(repository);
    	Game game1 = new Game();
    	game1.setGameId(1L);
    	game1.setGameName("test");
    	game1.setGameType(GameType.CLASSIC_GAME);
    	Mockito.when(repository.findById(null)).thenThrow(new IllegalArgumentException());
    	Mockito.when(repository.findById(1L)).thenReturn(Optional.of(game1));
    }

    @After
    public void tearDown()  {
    }

	@Test
	public void getGameTest() {
		Game game = service.getGame(1L);
		Assert.assertNotNull(game);
		Assert.assertTrue(game.getGameId().equals(1L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getGameNullTest() {
		service.getGame(null);
	}
}
