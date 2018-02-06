package dev.paie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class GeneralController {

	@RequestMapping(method = RequestMethod.GET , path = "/mvc/employes/lister")
	public void creerBulletinForm() {
		
	}	
}
