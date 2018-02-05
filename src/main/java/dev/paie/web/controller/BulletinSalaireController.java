package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepo;
	
	@Autowired
	private CalculerRemunerationService calculremService;
	
	@Autowired
	private PeriodeRepository periodeRepo;
	
	@Autowired
	private RemunerationEmployeRepository remEmplRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmployeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("listePeriode", periodeRepo.findAll());
		mv.addObject("listeEmploye", remEmplRepo.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView creerEmploye(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		ModelAndView mv = new ModelAndView();
		bulletinSalaireRepo.save(bulletinSalaire);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("listeBulletin", bulletinSalaireRepo.findAll());
		mv.addObject("calculBulletin", calculremService);
		return mv;
	}
}
