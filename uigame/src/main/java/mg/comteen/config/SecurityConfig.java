package mg.comteen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .formLogin()
	            .loginPage("/signin")
	            .loginProcessingUrl("/signin/authenticate")
	            .failureUrl("/signin?param.error=bad_credentials")
	        .and()
	            .logout()
	                .logoutUrl("/signout")
	                .deleteCookies("JSESSIONID")
	        .and()
	            .authorizeRequests()
	                .antMatchers("/resources/**").permitAll()
	                .antMatchers("/signin").permitAll()
	                .antMatchers("/**").authenticated()
	        .and()
	            .rememberMe()
	        .and()
	            .apply(new SpringSocialConfigurer()
	            	   .postLoginUrl("/index")
	            	   //.postFailureUrl("/error_login")
	            	   .signupUrl("/signup"));
	}
	
	
}
