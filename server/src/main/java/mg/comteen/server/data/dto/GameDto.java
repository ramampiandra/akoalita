package mg.comteen.server.data.dto;

import mg.comteen.jpa.entity.GameEntity;

public class GameDto {
	
	private long idGame;
	private long idPlayerOne;
	private long idPlayerTwo;
	private String status;
	private String state;

	public long getIdPlayerOne() {
		return idPlayerOne;
	}

	public void setIdPlayerOne(long idPlayerOne) {
		this.idPlayerOne = idPlayerOne;
	}

	public long getIdGame() {
		return idGame;
	}

	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}

	public long getIdPlayerTwo() {
		return idPlayerTwo;
	}

	public void setIdPlayerTwo(long idPlayerTwo) {
		this.idPlayerTwo = idPlayerTwo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static GameDto getInstance(GameEntity game) {
		GameDto gameDto = new GameDto();
		gameDto.setIdGame(game.getId());
        gameDto.setIdPlayerOne(game.getIdPlayerOne());
        gameDto.setIdPlayerTwo(game.getIdPlayerTwo());
        gameDto.setStatus(game.getGameStatus());
        gameDto.setState(game.getLastState());
        return gameDto;
	}
	
	
	
	

}
