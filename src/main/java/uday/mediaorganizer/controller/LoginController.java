package uday.mediaorganizer.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "LoginPage.jsp";
	}

	@RequestMapping(value = "/loginServer", method = RequestMethod.GET)
	public String loginServer(ModelMap model) {
		return "LoginPage.jsp";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("username", name);
		return "HomePage.jsp";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "LoginPage.jsp";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "LoginPage.jsp";
	}

}
