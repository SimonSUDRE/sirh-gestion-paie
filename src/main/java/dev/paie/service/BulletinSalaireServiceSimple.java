package dev.paie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.BulletinSalaireRepository;

@Service
public class BulletinSalaireServiceSimple implements BulletinSalaireService {

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepo;
	private Grade grade;
	private ProfilRemuneration profil;
	private Entreprise entreprise;
	
	@Override
	@Transactional
	public void getForVisualiser(Integer id) {
		BulletinSalaire b = bulletinSalaireRepo.findOne(id);
		grade = b.getRemunerationEmploye().getGrade();
		profil = b.getRemunerationEmploye().getProfilRemuneration();
		entreprise = b.getRemunerationEmploye().getEntreprise();
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public ProfilRemuneration getProfil() {
		return profil;
	}

	public void setProfil(ProfilRemuneration profil) {
		this.profil = profil;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
}
