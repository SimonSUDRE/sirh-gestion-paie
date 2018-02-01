package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		nouvelleCotisation.setId(null);
		em.persist(nouvelleCotisation);	
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		Cotisation cot = em.find(Cotisation.class, cotisation.getId());
		cot.setCode(cotisation.getCode());
		cot.setLibelle(cotisation.getLibelle());
		cot.setTauxPatronal(cotisation.getTauxPatronal());
		cot.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public void supprimer(Cotisation cotisation) {
		em.remove(em.find(Cotisation.class, cotisation.getId()));
	}

	@Override
	public List<Cotisation> lister() {
		return em.createQuery("FROM Cotisation c", Cotisation.class).getResultList();
	}
}
