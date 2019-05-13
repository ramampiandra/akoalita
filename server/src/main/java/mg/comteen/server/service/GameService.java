package mg.comteen.server.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.GameCore;
import mg.comteen.server.data.entity.Game;
import mg.comteen.server.repository.GameRepository;

@Service
@Transactional
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerService playerService;
	
	public Game createNewGame() {
		//Create game
        Game game = new Game();
        game.setCreated(new Date());
        game.setIdPlayerOne(playerService.getLoggedUser().getId());
        game.setGameStatus("WAITS_FOR_PLAYER");
        gameRepository.save(game);
        return game;
    }
	
	public Game findById(long id) {
		return gameRepository.findById(id).get();
	}
	
	public Game updateGame(Game game) {
		game.setIdPlayerTwo(playerService.getLoggedUser().getId());
		game.setGameStatus("IN_PROGRESS");
		return gameRepository.save(game);
	}
	
	public GameCore startGame(Game game) {
		GameCore gameCore = new GameCore(game.getIdPlayerOne(), game.getIdPlayerTwo());
		return gameCore;
	}
}
