package mg.comteen.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.comteen.server.data.entity.MoveHistory;
import mg.comteen.server.repository.MoveHistoryRepository;

@Service
@Transactional
public class MoveHistoryService {
	
	@Autowired
	private MoveHistoryRepository moveHistoryRepository;
	
	public MoveHistory findByPlayerId(long id) {
		return moveHistoryRepository.findByPlayerId(id)
									.orElseGet(MoveHistory::new);
	}
	
	public void save(MoveHistory moveHistory) {
		moveHistoryRepository.save(moveHistory);
	}

}
