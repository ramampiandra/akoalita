package mg.comteen.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mg.comteen.server.data.dto.PlayerDto;
import mg.comteen.server.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Long createAccount(@RequestBody PlayerDto newPlayerDTO) {
	   return playerService.createNewPlayer(newPlayerDTO);
	}

}
