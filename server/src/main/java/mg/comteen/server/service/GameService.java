package mg.comteen.server.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public Optional<GameEntity> findById(long id) {
		return gameRepository.findById(id);
	}
	
	public Optional<GameEntity> updateGame(GameEntity game) {
		game.setIdPlayerTwo(playerService.getLoggedUser().getId());
		game.setGameStatus("IN_PROGRESS");
		return Optional.of(gameRepository.save(game));
	}
	
	
	public Optional<GameEntity> updateStateGame(Long id, String state) {
		Optional<GameEntity> game = findById(id);
		if(game.isPresent()) {
			GameEntity gameEntity = game.get();
			gameEntity.setLastState(state);
			gameEntity = gameRepository.save(gameEntity);
			return Optional.of(gameEntity);
		}
		return Optional.empty();
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
	
	public Optional<GameEntity> findGameByIdAndGameStatus(Long Id, String gameStatus) {
		List<GameEntity> gameList = gameRepository.findGameByIdAndGameStatus(Id, gameStatus);
		if(gameList != null && gameList.size() > 0) {
			return Optional.of(gameList.get(0));
		}
		return Optional.empty();
	}
	
	public Optional<GameEntity> findGameByIdAndIdPlayerOneOrIdPlayerTwo(Long Id, Long idPlayer) {
		Optional<GameEntity> game = findById(Id);
		if(game.isEmpty()) {
			throw new FanoronaException("Game not found");
		}
		List<GameEntity> gameList = gameRepository.findGameByIdAndIdPlayerOneOrIdPlayerTwo(Id, idPlayer, idPlayer);
		if(gameList != null && gameList.size() > 0) {
			return Optional.of(gameList.get(0));
		}
		return Optional.empty();
	}
}
