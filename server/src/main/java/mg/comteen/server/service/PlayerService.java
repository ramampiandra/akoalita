package mg.comteen.server.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mg.comteen.server.config.UserPrincipal;
import mg.comteen.server.data.dto.PlayerDto;
import mg.comteen.server.data.entity.Game;
import mg.comteen.server.data.entity.Player;
import mg.comteen.server.repository.PlayerRepository;

@Service
@Transactional
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Long createNewPlayer(PlayerDto playerDTO) {
        Player newPlayer = new Player();
        newPlayer.setUserName(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerRepository.save(newPlayer);
        return newPlayer.getId();
    }

    public Player getLoggedUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepository.findOneByUserName(userPrincipal.getUsername());
    }
    
    public Player getByUserName(String userName) {
    	return playerRepository.findOneByUserName(userName);
    }
    public Player findById(long id) {
    	return playerRepository.findById(id).get();
    }
    
    public Player updatePlayer(Player player) {
    	return playerRepository.save(player);
    }
    public Player updatePlayerFromGame(Game game) {
    	//Update idGame on Player
        Player player = findById(getLoggedUser().getId());
        player.setGame(game);
        return updatePlayer(player);
    }

	
	

}
