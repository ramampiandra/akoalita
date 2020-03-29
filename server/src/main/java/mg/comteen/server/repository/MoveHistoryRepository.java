package mg.comteen.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.server.data.entity.MoveHistory;

@Repository
public interface MoveHistoryRepository extends CrudRepository<MoveHistory, Long> {

}
