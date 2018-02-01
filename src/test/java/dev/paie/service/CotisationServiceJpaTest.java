package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { JeuxDeDonneesConfig.class, JpaConfig.class, DataSourceMySQLConfig.class, CotisationServiceJpa.class })
@RunWith(SpringRunner.class)
@Scope("test")
public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;
	
	@Autowired
	@Qualifier("ep01")
	private Cotisation cotisation;
	
	@Autowired
	@Qualifier("sp01")
	private Cotisation cotisationUp;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		cotisation.setId(1);
		cotisationUp.setId(1);
		
		// sauvegarder une nouvelle cotisation
		cotisationService.sauvegarder(cotisation);
		
		// vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		Cotisation cotisationListe0 = cotisationService.lister().stream().filter(c -> c.getId() == cotisation.getId()).findAny().orElse(new Cotisation());
		assertThat(cotisationListe0).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToComparingFieldByField(cotisation);
 
		// modifier une cotisation
		cotisationService.mettreAJour(cotisationUp);
		
		// vérifier que les modifications sont bien prises en compte via la méthode lister
		Cotisation cotisationListe1 = cotisationService.lister().stream().filter(c -> c.getId() == cotisationUp.getId()).findAny().orElse(new Cotisation());
		assertThat(cotisationListe1).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToComparingFieldByField(cotisationUp);
		
		// vérifier que la suppression est bien prise en compte via la méthode lister
		cotisationService.supprimer(cotisation);
		List<Cotisation> listeCotisation = cotisationService.lister();
		assertThat(listeCotisation.size() == 0).isTrue();
	}

}
