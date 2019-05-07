package mg.comteen.common;

public abstract class Move {

	public static enum Type {
		CAPTURING_ADVANCE, CAPTURING_WITHDRAWALL, PAIKA, SACRIFICE
	}

	protected Type type;

	protected void setTypeMove(int typeMove) {
		if (typeMove == 1001) {
			type = Type.CAPTURING_ADVANCE;
		} else if (typeMove == 1002) {
			type = Type.CAPTURING_WITHDRAWALL;
		}
	}

	/**
	 * Get the next Position
	 * 
	 * @param direction
	 * @param p
	 * @return
	 */
	protected Position getNext(int direction, Position p) {
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
	 * Get the next Position
	 * 
	 * @param direction
	 * @param p
	 * @return
	 */
	protected boolean isMoveValid(int direction, Position p) {
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
		if (type != null && type == Type.CAPTURING_WITHDRAWALL) {
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
			param.setDirection(direction);
		} else {
			param.setStartProcessPosition(param.getNextPosition());
		}
	}

}
