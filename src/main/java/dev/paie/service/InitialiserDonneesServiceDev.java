package dev.paie.service;

import java.time.Year;
import java.util.stream.IntStream;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.DonneesConfig;
import dev.paie.entite.Periode;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialiser() {
		IntStream.rangeClosed(1, 12).forEach(i -> {
			Periode p = new Periode(Year.now().atMonth(i).atDay(i),
					Year.now().atMonth(i).atEndOfMonth());
			em.persist(p);
		});
		
		try(AnnotationConfigApplicationContext contextData = new AnnotationConfigApplicationContext(DonneesConfig.class)) {
			contextData.getBeansWithAnnotation(Entity.class).values().stream().forEach(em::persist);
		}
	}

}
