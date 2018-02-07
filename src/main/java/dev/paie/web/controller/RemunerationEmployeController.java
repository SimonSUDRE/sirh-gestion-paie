package dev.paie.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.util.PaieUtils;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired
	private RemunerationEmployeRepository remEmplRepo;
	
	@Autowired
	private EntrepriseRepository entreRepo;
	
	@Autowired
	private GradeRepository gradeRepo;
	
	@Autowired
	private ProfilRemunerationRepository profilRemRepo;
	
	@Autowired
	private PaieUtils pu;
	
	private Map<String, Boolean> isFieldsOK = new HashMap<>();
	private String[] okFields = {"matriculeOk", "entrepriseOk", "profilOk", "gradeOk"};
	
	public RemunerationEmployeController() {
		isFieldsOK.put(okFields[0], true);
		isFieldsOK.put(okFields[1], true); 
		isFieldsOK.put(okFields[2], true); 
		isFieldsOK.put(okFields[3], true);
	}
	
	@ModelAttribute("remunerationEmploye")
	public RemunerationEmploye getRemunerationEmploye() {
		return new RemunerationEmploye();
	}
	
	@Secured("UTILISATEUR")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView employe() {
		return new ModelAndView("redirect:/mvc/employes/lister");
	}
	
	@Secured("ADMINISTRATEUR")
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmployeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("listeEntreprise", entreRepo.findAll());
		mv.addObject("listeProfils", profilRemRepo.findAll());
		mv.addObject("listeGrades", gradeRepo.findAll());
		mv.addObject("pu", pu);
		mv.addAllObjects(isFieldsOK);
		return mv;
	}
	
	private Map<String, Boolean> formValidation(RemunerationEmploye remEmpl) {
		isFieldsOK.replace(okFields[0], Pattern.matches("[A-Z]+[0-9]+", remEmpl.getMatricule()));
		isFieldsOK.replace(okFields[1], remEmpl.getEntreprise() != null);
		isFieldsOK.replace(okFields[2], remEmpl.getProfilRemuneration() != null);
		isFieldsOK.replace(okFields[3], remEmpl.getGrade() != null);
		return isFieldsOK;
	}
	
	@Secured("ADMINISTRATEUR")
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerEmploye(@ModelAttribute("remunerationEmploye") RemunerationEmploye remunerationEmploye) {
		if(formValidation(remunerationEmploye).containsValue(false)) {
			ModelAndView mv = creerEmployeForm();
			mv.setStatus(HttpStatus.BAD_REQUEST);
			return mv;
		}
		try {
			remEmplRepo.save(remunerationEmploye);
			return listerEmploye();
		} catch (javax.persistence.PersistenceException e) {
			isFieldsOK.replace(okFields[1], false);
			isFieldsOK.replace(okFields[2], false);
			isFieldsOK.replace(okFields[3], false);
			ModelAndView mv = creerEmployeForm();
			mv.setStatus(HttpStatus.BAD_REQUEST);
			return mv;
		}
	}
	
	@Secured("UTILISATEUR")
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("listeEmploye", remEmplRepo.findAll());
		return mv;
	}
}