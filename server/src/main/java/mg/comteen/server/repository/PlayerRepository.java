package mg.comteen.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.jpa.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
	
	PlayerEntity findOneByUserName(String userName);
}
