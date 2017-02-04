package mg.comteen.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.security.crypto.encrypt.Encryptors;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	//The path pattern that ConnectController handles is "/connect/{providerId}".
    public ConnectController connectController(
                ConnectionFactoryLocator connectionFactoryLocator,
                ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
	
	/**
	 * https://developers.facebook.com/apps/914425018685625/dashboard/
	 */
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		final FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory("914425018685625","d32bd416c7e6c28c9d97d1efc78b77fe");
		facebookConnectionFactory.setScope("mail");
		facebookConnectionFactory.setScope("public_profile");
		cfConfig.addConnectionFactory(facebookConnectionFactory);
	}

	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	    JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
	            dataSource, connectionFactoryLocator, Encryptors.noOpText());
	    repository.setConnectionSignUp(new UserConnectionSignUp());
	    return repository;
	}
}