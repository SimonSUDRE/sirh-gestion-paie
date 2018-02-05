package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@ModelAttribute("remunerationEmploye")
	public RemunerationEmploye getRemunerationEmploye() {
		return new RemunerationEmploye();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmployeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("listeEntreprise", entreRepo.findAll());
		mv.addObject("listeProfils", profilRemRepo.findAll());
		mv.addObject("listeGrades", gradeRepo.findAll());
		mv.addObject("pu", pu);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView creerEmploye(@ModelAttribute("remunerationEmploye") RemunerationEmploye remunerationEmploye) {
		remEmplRepo.save(remunerationEmploye);
		return listerEmploye();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("listeEmploye", remEmplRepo.findAll());
		return mv;
	}
}