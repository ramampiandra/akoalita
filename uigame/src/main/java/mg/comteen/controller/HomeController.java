package mg.comteen.controller;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("version", "2017-01-01");
        return modelAndView;
    }
	@RequestMapping(value = "/index")
    public ModelAndView authenticateUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("username", auth.getName());
        return modelAndView;
    }
	
	/*@RequestMapping(value = "/authenticate/facebook", method = RequestMethod.GET)
    public String authenticateFacebook(HttpServletRequest httpServletRequest) {
        
    }*/
}
