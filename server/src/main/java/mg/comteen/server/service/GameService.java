package mg.comteen.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.server.data.entity.Game;
import mg.comteen.server.data.entity.Player;
import mg.comteen.server.repository.GameRepository;

@Service
@Transactional
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public Game createNewGame(Player player) {
        Game game = new Game();

        return game;
    }


}
