package mg.comteen.server.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameOnline {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
    @Column(name = "player_one_id", nullable = true)
    private Long idPlayerOne;
    
    @Column(name = "player_two_id", nullable = true)
    private Long idPlayerTwo;	

}
