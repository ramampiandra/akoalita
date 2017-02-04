package mg.comteen.service.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import mg.comteen.domain.entity.User;
import mg.comteen.repository.UserRepository;

@Service
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	private SocialUserDetails getSocialUserDetails(final User user) {
		return new SocialUserDetails() {
			public boolean isEnabled() {
				return true;
			}
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return false;
			}
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public String getUsername() {
				return user.getUsername();
			}
			
			public String getPassword() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getUserId() {
				return user.getUsername();
			}
		};
	}
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		final User user = userRepository.findUserByUserId(userId);
		return getSocialUserDetails(user);
	}
}
