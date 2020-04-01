package mg.comteen;

import mg.comteen.common.Parameter;
import mg.comteen.common.GameState;

/**
 * 
 * @author ramamj
 *
 */
public interface Game {

	GameState executeGameEngine(String states, Parameter param);
	
	void loadGame(GameLoading gameLoading);

	default void handleIAGame(String states) { 
		//TODO MinMax Implementation 
	}
	
}
