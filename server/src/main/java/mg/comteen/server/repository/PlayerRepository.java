package mg.comteen.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.server.data.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
	
    Player findOneByUserName(String userName);
}
