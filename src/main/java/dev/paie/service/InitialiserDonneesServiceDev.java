package dev.paie.service;

import java.time.Year;
import java.util.stream.IntStream;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.DonneesConfig;
import dev.paie.entite.Periode;
import dev.paie.entite.Utilisateur;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void initialiser() {
		IntStream.rangeClosed(1, 12).forEach(i -> {
			Periode p = new Periode(Year.now().atMonth(i).atDay(i), Year.now().atMonth(i).atEndOfMonth());
			em.persist(p);
		});

		try (AnnotationConfigApplicationContext contextData = new AnnotationConfigApplicationContext(
				DonneesConfig.class)) {
			contextData.getBeansWithAnnotation(Entity.class).values().stream().filter(o -> 
				!o.getClass().isInstance(Utilisateur.class)).forEach(em::persist);
			contextData.getBeansOfType(Utilisateur.class).values().stream().forEach(u -> {
				u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));
				em.persist(u);
			});
		}
	}

}
