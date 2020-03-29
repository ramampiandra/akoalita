package mg.comteen.common;

public class Result<T> {

	private T data;

	private boolean result = false;
	
	int lastDirection = 0;

	private String message = "Ok";

	// 1 game over , player "1" win
	// 2 game over, player "2" win
	// 0 processing
	int statut = 0;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public int getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(int lastDirection) {
		this.lastDirection = lastDirection;
	}
	

}
