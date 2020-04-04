package mg.comteen.common;

import java.util.ArrayList;
import java.util.List;

public class Move {

	public static enum Type {
		CAPTURING_ADVANCE, CAPTURING_WITHDRAWAL, PAIKA
	}

	protected Type type;

	public void setTypeMove(int typeMove) {
		if (typeMove == 1001) {
			type = Type.CAPTURING_ADVANCE;
		} else if (typeMove == 1002) {
			type = Type.CAPTURING_WITHDRAWAL;
		} else if(typeMove == 2001) {
			type = Type.PAIKA;
		}
	}
	
	/**
	 * 
	 * @param position
	 * @param direction
	 * @return
	 */
	public List<Position> getChildNodes(Position position, Direction direction) {
		List<Position> positionList = new ArrayList<Position>();
		for(int dir : direction.get()) {
			Position pos = getNext(dir, position);
			if(isMoveValid(dir, pos)) {
				positionList.add(pos);
			}
		}
		return positionList;
	}
	
	
	/**
	 * Get the next Position
	 * 
	 * @param direction
	 * @param p
	 * @return
	 */
	public Position getNext(int direction, Position p) {
		int yPoint = p.getY();
		int xPoint = p.getX();
		int yNextPoint = 0;
		int xNextPoint = 0;
		switch (direction) {
		case Direction.TOP_LEFT:
			yNextPoint = yPoint - 1;
			xNextPoint = xPoint - 1;
			break;
		case Direction.TOP_MIDDLE:
			yNextPoint = yPoint;
			xNextPoint = xPoint - 1;
			break;
		case Direction.TOP_RIGHT:
			yNextPoint = yPoint + 1;
			xNextPoint = xPoint - 1;
			break;
		case Direction.MIDDLE_LEFT:
			yNextPoint = yPoint - 1;
			xNextPoint = xPoint;
			break;
		case Direction.MIDDLE_RIGHT:
			yNextPoint = yPoint + 1;
			xNextPoint = xPoint;
			break;
		case Direction.BOTTOM_LEFT:
			yNextPoint = yPoint - 1;
			xNextPoint = xPoint + 1;
			break;
		case Direction.BOTTOM_MIDDLE:
			yNextPoint = yPoint;
			xNextPoint = xPoint + 1;
			break;
		case Direction.BOTTOM_RIGHT:
			yNextPoint = yPoint + 1;
			xNextPoint = xPoint + 1;
			break;
		}
		return new Position(xNextPoint, yNextPoint);
	}

	/**
	 * Get and validate the next Position by using the direction
	 * 
	 * @param direction
	 * @param p
	 * @return
	 */
	public boolean isMoveValid(int direction, Position p) {
		int yPoint = p.getY();
		int xPoint = p.getX();
		boolean result = false;
		switch (direction) {
		case Direction.TOP_LEFT:
			result = (xPoint >= 0 && yPoint >= 0);
			break;
		case Direction.TOP_MIDDLE:
			result = xPoint >= 0;
			break;
		case Direction.TOP_RIGHT:
			result = (xPoint >= 0 && yPoint <= 8);
			break;
		case Direction.MIDDLE_LEFT:
			result = yPoint >= 0;
			break;
		case Direction.MIDDLE_RIGHT:
			result = yPoint <= 8;
			break;
		case Direction.BOTTOM_LEFT:
			result = (xPoint <= 4 && yPoint >= 0);
			break;
		case Direction.BOTTOM_MIDDLE:
			result = xPoint <= 4;
			break;
		case Direction.BOTTOM_RIGHT:
			result = (xPoint <= 4 && yPoint <= 8);
			break;
		}
		return result;
	}
	
	/**
	 * Change direction for type move withdraw
	 * 
	 * @param param
	 * @return
	 */
	public void initMoveHandler(Parameter param) {
		int direction = param.getDirection();
		//Change to the inverse direction for withdrawal capture
		if (type != null && type == Type.CAPTURING_WITHDRAWAL) {
			switch (direction) {
				case Direction.TOP_LEFT:
					direction = Direction.BOTTOM_RIGHT;
					break;
				case Direction.TOP_MIDDLE:
					direction = Direction.BOTTOM_MIDDLE;
					break;
				case Direction.TOP_RIGHT:
					direction = Direction.BOTTOM_LEFT;
					break;
				case Direction.MIDDLE_LEFT:
					direction = Direction.MIDDLE_RIGHT;
					break;
				case Direction.MIDDLE_RIGHT:
					direction = Direction.MIDDLE_LEFT;
					break;
				case Direction.BOTTOM_LEFT:
					direction = Direction.TOP_RIGHT;
					break;
				case Direction.BOTTOM_MIDDLE:
					direction = Direction.TOP_MIDDLE;
					break;
				case Direction.BOTTOM_RIGHT:
					direction = Direction.TOP_LEFT;
					break;
			}
			param.setStartProcessPosition(param.getCurrentPosition());
			//Changing direction
			param.setDirection(direction);
		} else { 
			// No change for CAPTURING_ADVANCE and PAIKA move
			param.setStartProcessPosition(param.getNextPosition());
		}
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	

}
