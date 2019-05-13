package mg.comteen.server.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("idgame")
	private long idGame;
	private int source;
	private int destination;
	@JsonProperty("typemove")
	private int typeMove;
	@JsonProperty("board")
	private String stateBoard;
	
	
	public long getIdGame() {
		return idGame;
	}
	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public int getTypeMove() {
		return typeMove;
	}
	public void setTypeMove(int typeMove) {
		this.typeMove = typeMove;
	}
	public String getStateBoard() {
		return stateBoard;
	}
	public void setStateBoard(String stateBoard) {
		this.stateBoard = stateBoard;
	}
}
