package mg.comteen.server.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.GameCore;
import mg.comteen.GameLoading;
import mg.comteen.exception.FanoronaException;
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
	
	public Game updateStateGame(Long id, String state) {
		Game game = findById(id);
		game.setLastState(state);
		return gameRepository.save(game);
	}
	
	public GameCore startGame(Game game) {
		GameCore gameCore = new GameCore(game.getIdPlayerOne(), game.getIdPlayerTwo());
		return gameCore;
	}
	
	public GameCore reloadGame(Game game, GameLoading gameLoading) {
		GameCore gameCore = new GameCore(game.getIdPlayerOne(), game.getIdPlayerTwo(), gameLoading);
		return gameCore;
	}
	
	public Game findGameByIdAndGameStatus(Long Id, String gameStatus) {
		List<Game> gameList = gameRepository.findGameByIdAndGameStatus(Id, gameStatus);
		if(gameList != null && gameList.size() > 0) {
			return gameList.get(0);
		}
		return null;
	}
	
	public Game findGameByIdAndIdPlayerOneOrIdPlayerTwo(Long Id, Long idPlayer) {
		Game game = findById(Id);
		if(game == null) {
			throw new FanoronaException("Game not found");
		}
		List<Game> gameList = gameRepository.findGameByIdAndIdPlayerOneOrIdPlayerTwo(Id, idPlayer, idPlayer);
		if(gameList != null && gameList.size() > 0) {
			return gameList.get(0);
		}
		return null;
	}
}
