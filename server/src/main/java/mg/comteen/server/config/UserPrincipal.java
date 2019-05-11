package mg.comteen.server.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.common.collect.ImmutableSet;

import mg.comteen.server.data.entity.Player;

public class UserPrincipal extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserPrincipal(Player player) {
		super(player.getUserName(),
              player.getPassword(),
              true,
              true,
              true,
              true,
              ImmutableSet.of(new SimpleGrantedAuthority("create")));
	}

}
