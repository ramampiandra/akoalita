package mg.comteen;

import java.util.List;
/**
 * 
 * @author ramamj
 *
 */
public class GameLoading {
	
	// States of game
	private String state;
	
	// Player's one hystory
	private List<Integer> directionHasAlreadyDonePlayerOne;
	
	// Player's two hystory
	private List<Integer> directionHasAlreadyDonePlayerTwo;

	public List<Integer> getDirectionHasAlreadyDonePlayerOne() {
		return directionHasAlreadyDonePlayerOne;
	}

	public void setDirectionHasAlreadyDonePlayerOne(List<Integer> directionHasAlreadyDonePlayerOne) {
		this.directionHasAlreadyDonePlayerOne = directionHasAlreadyDonePlayerOne;
	}

	public List<Integer> getDirectionHasAlreadyDonePlayerTwo() {
		return directionHasAlreadyDonePlayerTwo;
	}

	public void setDirectionHasAlreadyDonePlayerTwo(List<Integer> directionHasAlreadyDonePlayerTwo) {
		this.directionHasAlreadyDonePlayerTwo = directionHasAlreadyDonePlayerTwo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	

}
