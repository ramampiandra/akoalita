package mg.comteen.server.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.common.collect.ImmutableSet;

import mg.comteen.jpa.entity.PlayerEntity;

public class UserPrincipal extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserPrincipal(PlayerEntity player) {
		super(player.getUserName(),
              player.getPassword(),
              true,
              true,
              true,
              true,
              ImmutableSet.of(new SimpleGrantedAuthority("create")));
	}

}
