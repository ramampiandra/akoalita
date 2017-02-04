package mg.comteen.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.web.context.request.NativeWebRequest;

public class SimpleSignInAdapter implements SignInAdapter {
	
    private final SocialUserDetailsService socialUserDetailsService;
    
    public SimpleSignInAdapter(SocialUserDetailsService socialUserDetailsService) {
        this.socialUserDetailsService = socialUserDetailsService;
    }

    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        SocialUserDetails userDetails = socialUserDetailsService.loadUserByUserId(localUserId);
        Authentication token = new SocialAuthenticationToken(connection, userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return null;
    }

}
