package mg.comteen;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mg.comteen.config.DataBaseConfig;
import mg.comteen.config.SecurityConfig;
import mg.comteen.config.SocialConfig;


@Configuration
@ComponentScan
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement
@Import(value = {mg.comteen.config.SocialConfig.class, 
		 		 mg.comteen.config.SecurityConfig.class,
		 		 mg.comteen.config.DataBaseConfig.class})
public class AppConfig {}
