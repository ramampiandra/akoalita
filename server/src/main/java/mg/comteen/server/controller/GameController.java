package mg.comteen.server.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mg.comteen.GameCore;
import mg.comteen.common.Parameter;
import mg.comteen.common.Result;
import mg.comteen.server.data.dto.ParameterDto;
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
	        responseDto.setData(game);
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
    @RequestMapping(value = "/move", consumes = "application/json", method = RequestMethod.POST)
    public ResponseDto<?> hangleGame(@RequestBody ParameterDto parameterDto) {
    	ResponseDto<Result<String>> responseDto = new ResponseDto<>();
    	
    	try { 
    		GameCore gameCore = (GameCore)httpSession.getAttribute(parameterDto.getIdGame() + "");
    		if(gameCore != null && !StringUtils.isEmpty(parameterDto.getStateBoard())) {
    			Parameter param = new Parameter();
    			param.setSourceStatePosition(parameterDto.getSource());
    			param.setDestStatePosition(parameterDto.getDestination());
    			param.setTypeMove(parameterDto.getTypeMove());
    			Result<String> res = gameCore.handleGame(parameterDto.getStateBoard(), param);
    			if(res.isResult()) responseDto.setStatus(false);
    			responseDto.setData(res);
    		} else {
    			responseDto.setMessage("Instance or configuration game not found : [" + parameterDto.getIdGame() + "]");
    		}
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	
    	return responseDto;
    }
    


}
