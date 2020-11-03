package mg.comteen.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.jpa.entity.MoveHistoryEntity;

@Repository
public interface MoveHistoryRepository extends CrudRepository<MoveHistoryEntity, Long> {
	
	Optional<MoveHistoryEntity> findByPlayerId(Long playerId);

}
