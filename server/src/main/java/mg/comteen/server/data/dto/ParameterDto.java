package mg.comteen.server.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
* Game position : 
* 01 02 03 04 05 06 07 08 09
* 10 11 12 13 14 15 16 17 18
* 19 20 21 22 23 24 25 26 27
* 28 29 30 31 32 33 34 35 36
* 37 38 39 40 41 42 43 44 45*/
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
