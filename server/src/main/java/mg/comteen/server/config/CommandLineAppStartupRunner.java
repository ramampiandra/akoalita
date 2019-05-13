package mg.comteen.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mg.comteen.server.data.dto.PlayerDto;
import mg.comteen.server.service.PlayerService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger LOG =
      LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
 
    @Autowired
    private PlayerService playerService;
    
    @Value("${akoalita.security.useradmin}")
    private String userAdmin;
    
    @Value("${akoalita.security.passadmin}")
    private String passwordAdmin;
    
    @Value("${akoalita.security.emailadmin}")
    private String emailAdmin;
    
    
    @Override
    public void run(String...args) throws Exception {
    	if(playerService.getByUserName(userAdmin) == null) {
    		PlayerDto playerDto = new PlayerDto();
            playerDto.setUserName(userAdmin);
            playerDto.setPassword(passwordAdmin);
            playerDto.setEmail(emailAdmin);
            playerService.createNewPlayer(playerDto);
    	} else {
    		LOG.info("[AKOALITA] Admin user already configured");
    	}
    }
}
