package mg.comteen.server.data.dto;

public class GameDto {
	
	private long idGame;
	private long idPlayerOne;
	private long idPlayerTwo;
	private String status;

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
	
	
	
	

}
