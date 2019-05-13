package mg.comteen.server.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mg.comteen.GameCore;
import mg.comteen.common.Parameter;
import mg.comteen.common.Result;
import mg.comteen.server.data.dto.ResponseDto;
import mg.comteen.server.data.entity.Game;
import mg.comteen.server.service.GameService;
import mg.comteen.server.service.PlayerService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDto<Game> createNewGame() {
    	ResponseDto<Game> responseDto = new ResponseDto<>();
    	try { 
	    	Game game = gameService.createNewGame();
	        playerService.updatePlayerFromGame(game);
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	return responseDto;
    }
    
    @RequestMapping(value = "/join/{idGame}", method = RequestMethod.GET)
    public ResponseDto<Game> joinGame(@PathVariable("idGame") long idGame) {
    	ResponseDto<Game> responseDto = new ResponseDto<>();
    	try { 
    		Game game = gameService.findById(idGame);
        	if(game != null) {
        		// Update game status
        		gameService.updateGame(game);
        		// Update idGame on Player two
        		playerService.updatePlayerFromGame(game);
        		// Start game
        		httpSession.setAttribute(game.getId() + "", gameService.startGame(game));
        	}
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	return responseDto;
    }
    @RequestMapping(value = "/move/{idGame}/{states}", method = RequestMethod.POST)
    public ResponseDto<?> hangleGame(@PathVariable(name = "idGame", value = "0", required = true) long idGame,
    									@PathVariable(name = "states", value = "0", required = true) String states,
    									Parameter param) {
    	ResponseDto<Result<String>> responseDto = new ResponseDto<>();
    	try { 
    		GameCore gameCore = (GameCore)httpSession.getAttribute(idGame + "");
    		if(gameCore != null) {
    			Result<String> res = gameCore.handleGame(states, param);
    			responseDto.setData(res);
    		} else {
    			responseDto.setMessage("Game not found : " + idGame);
    		}
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	return responseDto;
    }
    


}
