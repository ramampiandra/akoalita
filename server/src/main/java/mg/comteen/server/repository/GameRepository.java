package mg.comteen.server.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.comteen.server.data.entity.Game;


@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
	
	List<Game> findGameByIdAndGameStatus(Long Id, String gameStatus);
	
	@Query(name = "SELECT game FROM Game game " + 
			      "WHERE game.id = :id AND game.gameStatus = 'IN_PROGRESS' " + 
			      "AND (game.idPlayerOne = :idPlayerOne OR game.idPlayerTwo = :idPlayerTwo)")
	List<Game> findGameByIdAndIdPlayerOneOrIdPlayerTwo(@Param("id")Long Id, @Param("idPlayerOne")Long idPlayerOne, @Param("idPlayerTwo")Long idPlayerTwo);

}
