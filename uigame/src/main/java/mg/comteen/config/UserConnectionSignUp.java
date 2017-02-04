package mg.comteen.config;

import org.apache.log4j.Logger;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class UserConnectionSignUp implements ConnectionSignUp {
	
	private static final Logger logging = Logger.getLogger(UserConnectionSignUp.class);
	
	public String execute(Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		logging.debug("UserProfile : " + userProfile.getEmail() + "," + userProfile.getName() );
		return userProfile.getEmail();
	}
	
}