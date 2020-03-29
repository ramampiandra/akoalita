package mg.comteen;

import mg.comteen.common.Parameter;
import mg.comteen.common.Result;

/**
 * 
 * @author ramamj
 *
 */
public interface Game {

	Result<String> handleGame(String states, Parameter param);
	
	void loadGame(GameLoading gameLoading);

	default void handleIAGame(String states) { 
		//TODO MinMax Implementation 
	}
	
}
