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
import mg.comteen.GameLoading;
import mg.comteen.common.Parameter;
import mg.comteen.common.Result;
import mg.comteen.server.data.dto.GameDto;
import mg.comteen.server.data.dto.ParameterDto;
import mg.comteen.server.data.dto.ResponseDto;
import mg.comteen.server.data.entity.Game;
import mg.comteen.server.data.entity.GameStatus;
import mg.comteen.server.data.entity.Player;
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
    public ResponseDto<GameDto> createNewGame() {
    	ResponseDto<GameDto> responseDto = new ResponseDto<>();
   
    	try { 
    		Player player = playerService.findById(playerService.getLoggedUser().getId());
    		if(player != null) {
    			Game game = gameService.findGameByIdAndGameStatus(player.getGame().getId(), GameStatus.IN_PROGRESS.getValue());
    			if(game == null) {
    				game = gameService.createNewGame();
        	        playerService.updatePlayerFromGame(game);
    			} else {
    				responseDto.setMessage("You already subscribed in this game " + game.getId());
    				responseDto.setStatus(false);
    			}
    			
    			responseDto.setData(GameDto.getInstance(game));
    		}
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	
    	return responseDto;
    }
    
    /**
     * Join or rejoin
     * @param idGame
     * @return
     */
    @RequestMapping(value = "/join/{idGame}", method = RequestMethod.GET)
    public ResponseDto<GameDto> joinGame(@PathVariable("idGame") long idGame) {
    	ResponseDto<GameDto> responseDto = new ResponseDto<>();
    	
    	try {
    		// Find if player has already an IN PROGRESS game => rejoin method
    		Game game = gameService.findGameByIdAndIdPlayerOneOrIdPlayerTwo(idGame, playerService.getLoggedUser().getId());
    		if(game == null) {
    			game = gameService.findGameByIdAndGameStatus(idGame, GameStatus.WAITS_FOR_PLAYER.getValue());
            	if(game != null) {
            		// Update game status
            		gameService.updateGame(game);
            		// Update idGame on Player two
            		playerService.updatePlayerFromGame(game);
            		// Start game
            		httpSession.setAttribute(game.getId() + "", gameService.startGame(game));
            		
            		GameDto gameDto = GameDto.getInstance(game);
        	        responseDto.setData(gameDto);
            	} else {
            		responseDto.setMessage("Game is already in progress or finished ");
    				responseDto.setStatus(false);
            	}
    		} else {
    			GameCore gameCore = (GameCore)httpSession.getAttribute(idGame + "");
    			if(gameCore == null) {
    				GameLoading gameLoading = new GameLoading();
    				// Reload game
            		httpSession.setAttribute(game.getId() + "", gameService.reloadGame(game, gameLoading));
            		responseDto.setMessage("You rejoined the game " + idGame);
            		responseDto.setData(GameDto.getInstance(game));
    			} else {
    				responseDto.setMessage("You has already joined the game " + idGame);
    			}
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
    			param.setPhysicIdentityPlayer(playerService.getLoggedUser().getId());
    			
    			Result<String> res = gameCore.handleGame(parameterDto.getStateBoard(), param);
    			if(!res.isResult()) {
    				responseDto.setStatus(false);
    			} else {
    				// Update game status
            		gameService.updateStateGame(parameterDto.getIdGame(), res.getData());
    			}
    			responseDto.setData(res);
    		} else {
    			responseDto.setMessage("Instance or configuration game not found : [" + parameterDto.getIdGame() + "]");
    			responseDto.setStatus(false);
    		}
    	} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setMessage(e.getMessage());
		}
    	
    	return responseDto;
    }
    


}
