package mg.comteen.common;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id;
	
	private String userName;
	
	private String email;
	
	private boolean isPlayerReady = false;

	private Position lastPosition;

	private List<Integer> directionHasAlreadyDone;

	public Player() {
	}

	public Player(int id) {
		this.id = id;
	}
	
	public void resetStates() {
		this.lastPosition = null;
		this.directionHasAlreadyDone = null;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
