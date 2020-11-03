package mg.comteen.jpa.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PlayerEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String email;
	
	private String userName;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_game", nullable = true)
	private GameEntity game;
	
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player")
	private MoveHistoryEntity moveHistory;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GameEntity getGame() {
		return game;
	}

	public void setGame(GameEntity game) {
		this.game = game;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
}
