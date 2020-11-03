package mg.comteen.jpa.entity;

public enum GameStatus {
    WAITS_FOR_PLAYER("WAITS_FOR_PLAYER"),
    IN_PROGRESS("IN_PROGRESS"),
    FIRST_PLAYER_WON("FIRST_PLAYER_WON"),
    SECOND_PLAYER_WON("SECOND_PLAYER_WON"),
    TIE("TIE"),
    TIMEOUT("TIMEOUT");
    
    private String value;
	
	private GameStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}