package mg.comteen.common;

import java.io.Serializable;

import mg.comteen.exception.FanoronaException;

public class Parameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Direction of the piece
	private int direction = -1;

	// Destination position in the string state
	private int destStatePosition;

	// Source position in the string state
	private int sourceStatePosition = -1;

	// Type move
	private int typeMove = 1;
	
	// Current external player id
	private long realPlayerID; 

	// Current player
	private Player currentPlayer;

	// Current Position (Class), calculated field from sourceStatePosition
	private Position currentPosition;

	// Next Position (Class), calculated field from destStatePosition
	private Position nextPosition;

	// Starting position for processing type move
	private Position startProcessPosition;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Set direction using source and destination position
	 * LEFT, MIDDLE, RIGHT ...
	 * @param currentPosition
	 * @param destination
	 */
	public void setDirection() {
		if (direction == -1 && currentPosition != null && nextPosition != null) {
			int yNextPoint = nextPosition.getY(), xNextPoint = nextPosition.getX();
			int yPoint = currentPosition.getY();
			int xPoint = currentPosition.getX();

			if ((yNextPoint == yPoint - 1) 
					&& (xNextPoint == xPoint - 1)) {
				direction = Direction.TOP_LEFT;
			} else if ((yNextPoint == yPoint) 
					&& (xNextPoint == xPoint - 1)) {
				direction = Direction.TOP_MIDDLE;
			} else if ((yNextPoint == yPoint + 1) 
					&& (xNextPoint == xPoint - 1)) {
				direction = Direction.TOP_RIGHT;
			} else if ((yNextPoint == yPoint - 1) 
					&& (xNextPoint == xPoint)) {
				direction = Direction.MIDDLE_LEFT;
			} else if ((yNextPoint == yPoint + 1) 
					&& (xNextPoint == xPoint)) {
				direction = Direction.MIDDLE_RIGHT;
			} else if ((yNextPoint == yPoint - 1) 
					&& (xNextPoint == xPoint + 1)) {
				direction = Direction.BOTTOM_LEFT;
			} else if ((yNextPoint == yPoint) && (xNextPoint == xPoint + 1)) {
				direction = Direction.BOTTOM_MIDDLE;
			} else if ((yNextPoint == yPoint + 1) 
					&& (xNextPoint == xPoint + 1)) {
				direction = Direction.BOTTOM_RIGHT;
			}
		}
	}

	public int getTypeMove() {
		return typeMove;
	}

	public void setTypeMove(int typeMove) {
		this.typeMove = typeMove;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		if(currentPlayer.getRealPlayerID() == realPlayerID) {
			this.currentPlayer = currentPlayer;
		} else {
			throw new FanoronaException("It's not possible to move the other player's piece [Real Identity Not Matching]");
		}
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Position getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition(Position nextPosition) {
		this.nextPosition = nextPosition;
	}

	public int getSourceStatePosition() {
		return sourceStatePosition;
	}

	public void setSourceStatePosition(int sourceStatePosition) {
		this.sourceStatePosition = sourceStatePosition;
	}

	public int getDestStatePosition() {
		return destStatePosition;
	}

	public void setDestStatePosition(int destStatePosition) {
		this.destStatePosition = destStatePosition;
	}

	public Position getStartProcessPosition() {
		return startProcessPosition;
	}

	public void setStartProcessPosition(Position startProcessPosition) {
		this.startProcessPosition = startProcessPosition;
	}

	public long getRealPlayerID() {
		return realPlayerID;
	}

	public void setRealPlayerID(long realPlayerID) {
		this.realPlayerID = realPlayerID;
	}

	
	
	

}
