package mg.comteen.server.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.server.data.entity.MoveHistory;

@Repository
public interface MoveHistoryRepository extends CrudRepository<MoveHistory, Long> {
	
	Optional<MoveHistory> findByPlayerId(Long playerId);

}
