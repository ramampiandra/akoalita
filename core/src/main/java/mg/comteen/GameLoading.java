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
	private List<Integer> playerOneDirectionHistory;
	
	// Player's two hystory
	private List<Integer> playerTwoDirectionHistory;


	public List<Integer> getPlayerOneDirectionHistory() {
		return playerOneDirectionHistory;
	}

	public void setPlayerOneDirectionHistory(List<Integer> playerOneDirectionHistory) {
		this.playerOneDirectionHistory = playerOneDirectionHistory;
	}

	public List<Integer> getPlayerTwoDirectionHistory() {
		return playerTwoDirectionHistory;
	}

	public void setPlayerTwoDirectionHistory(List<Integer> playerTwoDirectionHistory) {
		this.playerTwoDirectionHistory = playerTwoDirectionHistory;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	

}
