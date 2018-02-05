package dev.paie.service;

import java.time.Year;
import java.util.Map;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.DonneesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialiser() {
		AnnotationConfigApplicationContext contextData = new AnnotationConfigApplicationContext(DonneesConfig.class);

		Map<String, Cotisation> cotisations = contextData.getBeansOfType(Cotisation.class);
		Map<String, Entreprise> entreprises = contextData.getBeansOfType(Entreprise.class);
		Map<String, Grade> grades = contextData.getBeansOfType(Grade.class);
		Map<String, ProfilRemuneration> profils = contextData.getBeansOfType(ProfilRemuneration.class);

		IntStream.rangeClosed(1, 12).forEach(i -> {
			Periode p = new Periode(Year.now().atMonth(i).atDay(i),
					Year.now().atMonth(i).atEndOfMonth());
			em.persist(p);
		});
		cotisations.forEach((key, value) -> em.persist(value));
		entreprises.forEach((key, value) -> em.persist(value));
		grades.forEach((key, value) -> em.persist(value));
		profils.forEach((key, value) -> em.persist(value));

		contextData.close();
	}

}
