package mg.comteen.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "move_history")
public class MoveHistoryEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "last_position", nullable = false)
	private int lastPosition;
	
	@Column(name = "direction_history", nullable = false)
	private String directionHistory;
	
	@Column(name="player_id")
	private long playerId;
	
	@OneToOne
	@JoinColumn(name = "player_id", insertable = false, updatable = false)
	private PlayerEntity player;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}

	public String getDirectionHistory() {
		return directionHistory;
	}

	public void setDirectionHistory(String directionHistory) {
		this.directionHistory = directionHistory;
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
	

}
