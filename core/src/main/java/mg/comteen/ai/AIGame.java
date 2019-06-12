package mg.comteen.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import mg.comteen.GameCore;
import mg.comteen.common.Direction;
import mg.comteen.common.Move;
import mg.comteen.common.Position;

/**
 * 
 * @author ramamj
 *
 */
public class AIGame {
	
	private final int DEPTH_SEARCH = 3;
	
	private Direction direction = () -> Arrays.asList(Direction.TOP_LEFT, Direction.TOP_MIDDLE, 
										              Direction.TOP_RIGHT, Direction.MIDDLE_LEFT, Direction.MIDDLE_RIGHT, 
											          Direction.BOTTOM_LEFT, Direction.BOTTOM_MIDDLE, Direction.BOTTOM_RIGHT);
	
	/**
	 * WeightValue
	 * @author ramamj
	 *
	 */
	private class WeightValue {
		int value = 0;
		List<Position> tree;
		List<Position> arrTemp = new ArrayList<Position>();
		
		WeightValue(int value, Position pos) {
			this.value = value;
			tree = new ArrayList<Position>();
			tree.add(pos);
		}
		
		WeightValue getMax(WeightValue w1) {
			if(this.value < w1.value) {
				this.value = w1.value;
				arrTemp = w1.tree;
			}
			return this;
		}
		
		WeightValue udpate() {
			tree.addAll(arrTemp);
			arrTemp = new ArrayList<Position>();
			return this;
		}
	}

	public Position getOptimalNextMove(GameCore game) {
		minmax(true, DEPTH_SEARCH, game, game.getCurrentPosition());
		return null;
	}
	
	private WeightValue minmax(boolean isCurrentPlayer, int depth, GameCore game, Position curr) {
		if(depth == 0) {
			return new WeightValue(100, curr);
		}
		WeightValue weightValue = new WeightValue(isCurrentPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE , curr);

		for(Position pos : getChildNodes(curr,game.getInstanceRules().getInstanceMove())) {
			if(game.getBoard()[pos.getX()][pos.getY()] == 0) {
				if(isCurrentPlayer) {
					weightValue = weightValue.getMax(minmax(false, depth-1, game, pos));
				} else {
					weightValue =  weightValue.getMax(minmax(true, depth-1, game, pos));
				}
			}
		}
		return weightValue.udpate();
	}
	
	private List<Position> getChildNodes(Position currentPosition, Move move) {
		return move.getChildNodes(currentPosition, direction);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	

}
