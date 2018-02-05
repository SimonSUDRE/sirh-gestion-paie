package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@ModelAttribute("bulletinSalaire")
	public BulletinSalaire getBulletinSalaire() {
		return new BulletinSalaire();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmployeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("listePeriode", periodeRepo.findAll());
		mv.addObject("listeEmploye", remEmplRepo.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerEmploye(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		bulletinSalaireRepo.save(bulletinSalaire);
		return listerEmploye();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("listeBulletin", calculremService.mapped());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{id}")
	public ModelAndView visualiserEmploye(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("bulletin", bulletinSalaireRepo.findOne(id));
		mv.addObject("calculBulletin", calculremService.resultat(id));
		return mv;
	}
}
