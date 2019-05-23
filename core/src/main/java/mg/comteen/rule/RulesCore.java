package mg.comteen.rule;

import mg.comteen.common.Move;
import mg.comteen.common.Move.Type;
import mg.comteen.common.Parameter;
import mg.comteen.common.Player;
import mg.comteen.common.Position;
import mg.comteen.exception.FanoronaException;

/**
 * Implementation of the rules Singleton
 * 
 * @author ramamj
 *
 */
public class RulesCore implements Rules {
	
	//One to one with Move
	private Move move = new Move();

	public Move getInstanceMove() {
		return move;
	}

	/**
	 * Check if the next position is valid Next stone empty, axis (x, y) is not
	 * out of range
	 */
	public boolean checkIfNextPositionValid(int[][] board, Parameter param) {
		Position next = param.getNextPosition();
		boolean isValid = false;
		if(!isTheSamePieceForRelayCapturing(param)) {
			throw new FanoronaException("Invalid piece move for this relay capturing, use the last piece");
		}
		if (isCoordinateValid(board, param)) {
			if (isPositionValid(param)) {
				isValid = true;
			} else {
				throw new FanoronaException("Invalid move destination : " + next);
			}
		} else {
			throw new FanoronaException("Position out of range or destination position is not empty : " + next);
		}
		return isValid;
	}

	/**
	 * Processing move, change and return boolean value Consider the two moves,
	 * either percussion or
	 * 
	 */
	public boolean processChange(int[][] board, Parameter param) {
		boolean res = false;
		int oldX = param.getCurrentPosition().getX();
		int oldY = param.getCurrentPosition().getY();
		int x = param.getNextPosition().getX();
		int y = param.getNextPosition().getY();
		if (checkIfNextPositionValid(board, param)) {
			
			//First move for the piece
			board[x][y] = board[oldX][oldY];
			board[oldX][oldY] = 0;
			
			move.setTypeMove(param.getTypeMove());
			//No capture for PAIKA move
			if(move.getType() != Type.PAIKA) {
				captureOpponent(board, param);
			}
			
			// Set last new position valid for current player
			param.getCurrentPlayer().setLastPosition(param.getCurrentPosition());
			param.getCurrentPlayer().setCurrentPositionPiece(param.getNextPosition());
			res = true;
		}
		return res;
	}

	/**
	 * The capture is based on direction of the piece and we replace the
	 * Opponent piece by 0 i.e empty
	 */
	public void captureOpponent(int[][] board, Parameter param) {
		//move.setTypeMove(param.getTypeMove());
		int x = param.getNextPosition().getX(), y = param.getNextPosition().getY();
		int player = board[x][y];// Get player Id

		// Init the starting position
		move.initMoveHandler(param);
		int direction = param.getDirection();
		Position position = move.getNext(direction, param.getStartProcessPosition());

		// Starting ...
		while (move.isMoveValid(direction, position)) {
			int item = board[position.getX()][position.getY()];
			 // For item == 0, you can capture any UNBROKEN line of black pieces in this waa
			if (item == player || item == 0) {
				break;
			} else {
				// Capture pieces opponent 
				board[position.getX()][position.getY()] = 0;
			}
			position = move.getNext(direction, position);
		}
	}
	
	/**
	 * 
	 * @param board
	 * @param param
	 * @return
	 */
	private boolean isCoordinateValid(int[][] board, Parameter param) {
		Position next = param.getNextPosition();
		int x = next.getX();
		int y = next.getY();
		return  (x >= 0 && x <= 4) // x (row) must be between 0 and 4
				&& (y >= 0 && y <= 8) // y (column) must be between 0 and 8
				&& board[x][y] == 0; // the new (x,y)  must be empty, Pieces can only move onto empty spaces
	}
	
	/**
	 * Checking the piece id for relay capturing
	 * @param param
	 * @return
	 */
	private boolean isTheSamePieceForRelayCapturing(Parameter param) {
		Player player = param.getCurrentPlayer(); // Get the current player
		Position currentPosition = param.getCurrentPosition();
		if (player.getCurrentPositionPiece() != null && !player.getCurrentPositionPiece().equals(currentPosition)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	private boolean isPositionValid(Parameter param) {
		Player player = param.getCurrentPlayer(); // Get the current player
		Position next = param.getNextPosition(); // Get the next position
		return !player.isEqualToLastPosition(next) // Player can't move on the direct previous position 
				&& player.isValidDirection(param.getDirection()); // The piece can't make same direction during a relay capture
	}

}
