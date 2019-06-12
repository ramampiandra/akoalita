package mg.comteen.common;

public enum TypePlayer {
	
	HUMAN("HUMAN"),
	COMPUTER("COMPUTER");
	
	private TypePlayer(String value) {
		
	}
	private String value;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
