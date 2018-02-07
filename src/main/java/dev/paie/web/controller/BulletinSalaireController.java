package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import dev.paie.util.PaieUtils;

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
	
	@Autowired
	private PaieUtils pu;

	private Map<String, Boolean> isFieldsOK = new HashMap<>();
	private String[] okFields = {"periodeOk", "remEmplOk", "primeOk"};
	
	public BulletinSalaireController() {
		isFieldsOK.put(okFields[0], true);
		isFieldsOK.put(okFields[1], true);
		isFieldsOK.put(okFields[2], true);
	}
	
	@ModelAttribute("bulletinSalaire")
	public BulletinSalaire getBulletinSalaire() {
		return new BulletinSalaire();
	}
	
	@Secured("UTILISATEUR")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView bulletin() {
		return new ModelAndView("redirect:/mvc/bulletins/lister");
	}
	
	@Secured("ADMINISTRATEUR")
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletinForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("listePeriode", periodeRepo.findAll());
		mv.addObject("listeEmploye", remEmplRepo.findAll());
		mv.addAllObjects(isFieldsOK);	
		return mv;
	}
	
	private Map<String, Boolean> formValidation(BulletinSalaire bulletin) {
		isFieldsOK.replace(okFields[0], bulletin.getPeriode() != null);
		isFieldsOK.replace(okFields[1], bulletin.getRemunerationEmploye() != null);
		isFieldsOK.replace(okFields[2], bulletin.getPrimeExceptionnelle().compareTo(BigDecimal.ZERO) >= 0);
		return isFieldsOK;
	}
	
	@Secured("ADMINISTRATEUR")
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerBulletin(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		if(formValidation(bulletinSalaire).containsValue(false)) {
			ModelAndView mv = creerBulletinForm();
			mv.setStatus(HttpStatus.BAD_REQUEST);
			return mv;
		}
		try {
			bulletinSalaireRepo.save(bulletinSalaire);
			return listerBulletin();
		}catch(javax.persistence.PersistenceException e) {
			isFieldsOK.replace(okFields[0], false);
			isFieldsOK.replace(okFields[1], false);
			ModelAndView mv = creerBulletinForm();
			mv.setStatus(HttpStatus.BAD_REQUEST);
			return mv;
		}
	}
	
	@Secured("UTILISATEUR")
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("listeBulletin", calculremService.mapped());
		return mv;
	}
	
	@Secured("UTILISATEUR")
	@Transactional
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{id}")
	public ModelAndView visualiserBulletin(@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("calculBulletin", calculremService.resultat(Integer.parseInt(id)));
		mv.addObject("bulletin", bulletinSalaireRepo.findOne(Integer.parseInt(id)));
		mv.addObject("pu", pu);
		return mv;
	}
}
