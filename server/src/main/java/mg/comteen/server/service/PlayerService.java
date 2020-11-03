package mg.comteen.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mg.comteen.jpa.entity.GameEntity;
import mg.comteen.jpa.entity.PlayerEntity;
import mg.comteen.server.config.UserPrincipal;
import mg.comteen.server.data.dto.PlayerDto;
import mg.comteen.server.repository.PlayerRepository;

@Service
@Transactional
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Long createNewPlayer(PlayerDto playerDTO) {
        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setUserName(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerRepository.save(newPlayer);
        return newPlayer.getId();
    }

    public PlayerEntity getLoggedUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(userPrincipal.getUsername());
    }
    
    public PlayerEntity getByUserName(String userName) {
    	return playerRepository.findOneByUserName(userName);
    }
    public PlayerEntity findById(long id) {
    	return playerRepository.findById(id).get();
    }
    
    public PlayerEntity updatePlayer(PlayerEntity player) {
    	return playerRepository.save(player);
    }
    public PlayerEntity updatePlayerFromGame(GameEntity game) {
    	//Update idGame on Player
        PlayerEntity player = findById(getLoggedUser().getId());
        player.setGame(game);
        return updatePlayer(player);
    }

	
	

}
