package mg.comteen.server.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mg.comteen.server.data.entity.Game;


@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
	
	List<Game> findGameByIdAndGameStatus(Long Id, String gameStatus);

}
