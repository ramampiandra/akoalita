package mg.comteen.server.controller;

import java.util.Optional;

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
import mg.comteen.jpa.entity.GameEntity;
import mg.comteen.jpa.entity.GameStatus;
import mg.comteen.jpa.entity.MoveHistoryEntity;
import mg.comteen.jpa.entity.PlayerEntity;
import mg.comteen.common.GameState;
import mg.comteen.server.data.dto.GameDto;
import mg.comteen.server.data.dto.ParameterDto;
import mg.comteen.server.data.dto.ResponseDto;
import mg.comteen.server.service.GameService;
import mg.comteen.server.service.MoveHistoryService;
import mg.comteen.server.service.PlayerService;

@RestController
@RequestMapping("/game")
public class GameController {
	
    @Autowired
    private GameService gameService;
	
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private MoveHistoryService moveHistoryService;
	
    @Autowired
    private HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDto<GameDto> createNewGame() {
    	
    	ResponseDto<GameDto> responseDto = new ResponseDto<>();
   
    	try { 
    		PlayerEntity player = playerService.findById(playerService.getLoggedUser().getId());
    		if(player != null) {
    			Optional<GameEntity> game = gameService.findGameByIdAndGameStatus(player.getGame().getId(), GameStatus.IN_PROGRESS.getValue());
    			GameEntity gameEntity = null;
    			if(game.isEmpty()) {
    				gameEntity = gameService.createNewGame();
        	        playerService.updatePlayerFromGame(gameEntity);
    			} else {
    				gameEntity = game.get();
    				responseDto.setMessage("You already subscribed in this game " + gameEntity.getId());
    				responseDto.setStatus(false);
    			}
    			
    			responseDto.setData(GameDto.getInstance(gameEntity));
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
    		Optional<GameEntity> game = gameService.findGameByIdAndIdPlayerOneOrIdPlayerTwo(idGame, playerService.getLoggedUser().getId());
    		if(game.isEmpty()) {
    			game = gameService.findGameByIdAndGameStatus(idGame, mg.comteen.jpa.entity.GameStatus.WAITS_FOR_PLAYER.getValue());
            	if(game.isPresent()) {
            		GameEntity gameEntity = game.get();
            		// Update game status
            		gameService.updateGame(gameEntity);
            		// Update idGame on Player two
            		playerService.updatePlayerFromGame(gameEntity);
            		// Start game
            		httpSession.setAttribute(gameEntity.getId() + "", gameService.startGame(gameEntity));
            		
            		GameDto gameDto = GameDto.getInstance(gameEntity);
        	        responseDto.setData(gameDto);
            	} else {
            		responseDto.setMessage("Game is already in progress or finished ");
    				responseDto.setStatus(false);
            	}
    		} else {
    			GameCore gameCore = (GameCore)httpSession.getAttribute(idGame + "");
        		GameEntity gameEntity = game.get();

    			if(gameCore == null) {
    				GameLoading gameLoading = new GameLoading();
    				// Reload game
            		httpSession.setAttribute(gameEntity.getId() + "", gameService.reloadGame(gameEntity, gameLoading));
            		responseDto.setMessage("You rejoined the game " + idGame);
            		responseDto.setData(GameDto.getInstance(gameEntity));
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
    
    /**
     * 
     * @param parameterDto
     * @return
     */
    @RequestMapping(value = "/move", consumes = "application/json", method = RequestMethod.POST)
    public ResponseDto<?> hangleGame(@RequestBody ParameterDto parameterDto) {
    	ResponseDto<GameState> responseDto = new ResponseDto<>();
    	
    	try { 
    		GameCore gameCore = (GameCore)httpSession.getAttribute(parameterDto.getIdGame() + "");
    		if(gameCore != null && !StringUtils.isEmpty(parameterDto.getStateBoard())) {
    			Parameter param = new Parameter();
    			param.setSourceStatePosition(parameterDto.getSource());
    			param.setDestStatePosition(parameterDto.getDestination());
    			param.setTypeMove(parameterDto.getTypeMove());
    			param.setRealPlayerID(playerService.getLoggedUser().getId());
    			
    			//Call akoalita core game library
    			GameState res = gameCore.executeGameEngine(parameterDto.getStateBoard(), param);
    			if(!res.isResult()) {
    				responseDto.setStatus(false);
    			} else {
                    // Update game status
                    gameService.updateStateGame(parameterDto.getIdGame(), res.getState());
                    
                    //Update MoveHistory
                    MoveHistoryEntity moveHistory = moveHistoryService.findByPlayerId(playerService.getLoggedUser().getId());
                    if(moveHistory.getId() == null) {
                    	moveHistory.setPlayerId(playerService.getLoggedUser().getId());
                    	moveHistory.setDirectionHistory("");
                    }
                	moveHistory.setLastPosition(parameterDto.getDestination());
                	moveHistory.setDirectionHistory(moveHistory.getDirectionHistory() + res.getLastDirection() + "#");
                	moveHistoryService.save(moveHistory);
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
