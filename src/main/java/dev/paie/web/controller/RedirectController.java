package dev.paie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/mvc", ""})
public class RedirectController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView creerBulletinForm() {
		return new ModelAndView("redirect:/mvc/employes/lister");
	}	
}
