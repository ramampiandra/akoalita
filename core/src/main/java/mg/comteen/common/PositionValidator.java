package mg.comteen.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PositionValidator {
	
	// Map all possible move from source to destination, index-based
	private final static ConcurrentHashMap<Integer, List<Integer>> POSITION_VALIDATOR_MAP = new ConcurrentHashMap<>();
	
	static {
		int evenCount = 1;
		for(int k = 1; k < 46; k++,evenCount++) {
			POSITION_VALIDATOR_MAP.put(k, new ArrayList<>());
			int row = k % 9 == 0 ? k/9 : k/9 + 1;
			int i = k - 10;
			int z = row - 1;
			if(evenCount % 2 == 1) {
				for(; i < k - 7; i++) {
					addPositionToMap(i, k, z);
				}
				for(i = k - 1, z = row ; i < k + 2; i++) {
					addPositionToMap(i, k, z);
				}
				for(i = k + 8,z = row + 1; i < k + 11; i++) {
					addPositionToMap(i, k, z);
				}
			} else {
				addPositionToMap(k - 9, k, row - 1);
				addPositionToMap(k + 9, k, row + 1);
				for(i = k - 1, z = row ; i < k + 2; i++) {
					addPositionToMap(i, k, z);
				}
			}

		}
	}
	private static void addPositionToMap(int i, int k, int row) {
		int rowVal = i % 9 == 0 ? i/9 : i/9 + 1;
		if(i > 0 && i != k 
				 && rowVal == row
				 && i < 46) {
			POSITION_VALIDATOR_MAP.get(k).add(i);
		}
	}

	public static ConcurrentHashMap<Integer, List<Integer>> getPositionValidatorMap() {
		return POSITION_VALIDATOR_MAP;
	}
	
	
}
