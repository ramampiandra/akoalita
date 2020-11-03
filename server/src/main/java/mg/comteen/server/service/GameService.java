package mg.comteen.server.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.GameCore;
import mg.comteen.GameLoading;
import mg.comteen.exception.FanoronaException;
import mg.comteen.jpa.entity.GameEntity;
import mg.comteen.server.repository.GameRepository;

@Service
@Transactional
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerService playerService;
	
	public GameEntity createNewGame() {
		//Create game
		GameEntity game = new GameEntity();
        game.setCreated(new Date());
        game.setIdPlayerOne(playerService.getLoggedUser().getId());
        game.setGameStatus("WAITS_FOR_PLAYER");
        gameRepository.save(game);
        return game;
    }
	
	public GameEntity findById(long id) {
		return gameRepository.findById(id).get();
	}
	
	public GameEntity updateGame(GameEntity game) {
		game.setIdPlayerTwo(playerService.getLoggedUser().getId());
		game.setGameStatus("IN_PROGRESS");
		return gameRepository.save(game);
	}
	
	public GameEntity updateStateGame(Long id, String state) {
		GameEntity game = findById(id);
		game.setLastState(state);
		return gameRepository.save(game);
	}
	
	public GameCore startGame(GameEntity game) {
		GameCore gameCore = new GameCore(game.getIdPlayerOne(), game.getIdPlayerTwo());
		return gameCore;
	}
	
	public GameCore reloadGame(GameEntity game, GameLoading gameLoading) {
		GameCore gameCore = new GameCore(game.getIdPlayerOne(), game.getIdPlayerTwo());
		gameCore.loadGame(gameLoading);
		return gameCore;
	}
	
	public GameEntity findGameByIdAndGameStatus(Long Id, String gameStatus) {
		List<GameEntity> gameList = gameRepository.findGameByIdAndGameStatus(Id, gameStatus);
		if(gameList != null && gameList.size() > 0) {
			return gameList.get(0);
		}
		return null;
	}
	
	public GameEntity findGameByIdAndIdPlayerOneOrIdPlayerTwo(Long Id, Long idPlayer) {
		GameEntity game = findById(Id);
		if(game == null) {
			throw new FanoronaException("Game not found");
		}
		List<GameEntity> gameList = gameRepository.findGameByIdAndIdPlayerOneOrIdPlayerTwo(Id, idPlayer, idPlayer);
		if(gameList != null && gameList.size() > 0) {
			return gameList.get(0);
		}
		return null;
	}
}
