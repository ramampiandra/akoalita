package mg.comteen.common;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author ramamj
 *
 */
public interface Direction extends Supplier<List<Integer>> {
	
	int TOP_LEFT = 1;
	int TOP_MIDDLE = 2;
	int TOP_RIGHT = 3;
	int MIDDLE_LEFT = 4;
	int MIDDLE_RIGHT = 5;
	int BOTTOM_LEFT = 6;
	int BOTTOM_MIDDLE = 7;
	int BOTTOM_RIGHT = 8;
	
}
