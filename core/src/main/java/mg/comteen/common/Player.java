package mg.comteen.common;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id;
	
	private long physicIdentity;
	
	private TypePlayer typePlayer;
	
	// Used for validation only if player move several times during the same turn (Relay Capturing)
	private Position lastPosition;
	
	// Player can't move more than one unique piece for relay Capturing
	private Position currentPositionPiece;

	private List<Integer> directionHasAlreadyDone;

	public Player() {
	}

	public Player(int id) {
		this.id = id;
	}
	
	public void resetStates() {
		this.lastPosition = null;
		this.directionHasAlreadyDone = null;
		this.currentPositionPiece = null;
	}
	
	public void updateStates(Position lastPosition, List<Integer> directionHasAlreadyDone) {
		this.lastPosition = lastPosition;
		this.directionHasAlreadyDone = directionHasAlreadyDone;
	}

	public boolean isEqualToLastPosition(Position current) {
		boolean isEqual = false;
		if(lastPosition == null) {
			lastPosition = current;
			return false;
		}
		if (lastPosition.getX() == current.getX() && lastPosition.getY() == current.getY()) {
			isEqual = true;
			lastPosition = new Position(current.getX(), current.getY());
		}
		return isEqual;
	}
	
	/**
	 * Check if direction is valid for this user
	 * @param direction
	 * @return
	 */
	public boolean isValidDirection(int direction) {
		if(directionHasAlreadyDone == null) {
			directionHasAlreadyDone = new ArrayList<Integer>();
		}
		if(!directionHasAlreadyDone.contains(direction)) {
			directionHasAlreadyDone.add(direction);
			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Position getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}

	public long getPhysicIdentity() {
		return physicIdentity;
	}

	public void setPhysicIdentity(long physicIdentity) {
		this.physicIdentity = physicIdentity;
	}

	public Position getCurrentPositionPiece() {
		return currentPositionPiece;
	}

	public void setCurrentPositionPiece(Position currentPositionPiece) {
		this.currentPositionPiece = currentPositionPiece;
	}

	public TypePlayer getTypePlayer() {
		return typePlayer;
	}

	public void setTypePlayer(TypePlayer typePlayer) {
		this.typePlayer = typePlayer;
	}
}
