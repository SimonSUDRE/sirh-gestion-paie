package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.BulletinSalaireService;
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
	private BulletinSalaireService bulletinService;
	
	@Autowired
	private PaieUtils pu;

	private Map<String, Boolean> isFieldsOK = new HashMap<>();
	
	public BulletinSalaireController() {
		isFieldsOK.put("periodeOk", true);
		isFieldsOK.put("remEmplOk", true);
		isFieldsOK.put("primeOk", true);
	}
	
	@ModelAttribute("bulletinSalaire")
	public BulletinSalaire getBulletinSalaire() {
		return new BulletinSalaire();
	}
	
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
		isFieldsOK.replace("periodeOk", bulletin.getPeriode() != null);
		isFieldsOK.replace("remEmplOk", bulletin.getRemunerationEmploye() != null);
		isFieldsOK.replace("primeOk", bulletin.getPrimeExceptionnelle().compareTo(BigDecimal.ZERO) >= 0);
		return isFieldsOK;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerBulletin(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletinSalaire) {
		if(formValidation(bulletinSalaire).containsValue(false)) {
			return creerBulletinForm();
		}
		bulletinSalaireRepo.save(bulletinSalaire);
		return listerBulletin();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("listeBulletin", calculremService.mapped());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{id}")
	public ModelAndView visualiserBulletin(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("bulletin", bulletinSalaireRepo.findOne(id));
		mv.addObject("calculBulletin", calculremService.resultat(id));
		bulletinService.getForVisualiser(id);
		mv.addObject("infoBulletin", bulletinService);
		mv.addObject("pu", pu);
		return mv;
	}
}
