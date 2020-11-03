package mg.comteen.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GameEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "game_status", nullable = false)
    private String gameStatus;
    
    @Column(name = "player_one_id", nullable = true)
    private Long idPlayerOne;
    
    @Column(name = "player_two_id", nullable = true)
    private Long idPlayerTwo;
    
    @Column(name = "last_states", nullable = true)
    private String lastState;

    @Column(name = "created", nullable = false)
    private Date created;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<PlayerEntity> players;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getIdPlayerOne() {
		return idPlayerOne;
	}

	public void setIdPlayerOne(Long idPlayerOne) {
		this.idPlayerOne = idPlayerOne;
	}

	public Long getIdPlayerTwo() {
		return idPlayerTwo;
	}

	public void setIdPlayerTwo(Long idPlayerTwo) {
		this.idPlayerTwo = idPlayerTwo;
	}

	public List<PlayerEntity> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}

	public String getLastState() {
		return lastState;
	}

	public void setLastState(String lastState) {
		this.lastState = lastState;
	}
}
