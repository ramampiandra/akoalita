package mg.comteen.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.jpa.entity.MoveHistoryEntity;
import mg.comteen.server.repository.MoveHistoryRepository;

@Service
@Transactional
public class MoveHistoryService {
	
	@Autowired
	private MoveHistoryRepository moveHistoryRepository;
	
	public MoveHistoryEntity findByPlayerId(long id) {
		return moveHistoryRepository.findByPlayerId(id)
									.orElseGet(MoveHistoryEntity::new);
	}
	
	public void save(MoveHistoryEntity moveHistory) {
		moveHistoryRepository.save(moveHistory);
	}

}
