package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
@Scope("test")
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Avantage avantage = new Avantage();
		avantage.setCode("code1");
		avantage.setNom("avantage1");
		avantage.setMontant(new BigDecimal("25.45"));
		
		// sauvegarder un nouvel avantage
		avantageRepository.save(avantage);
		
		// vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		Avantage avantageListe0 = avantageRepository.getByCode(avantage.getCode()).stream().filter(a -> a.getCode().equals(avantage.getCode())).findAny().orElse(new Avantage());
		assertThat(avantageListe0).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToIgnoringGivenFields(avantage, "id");		
		
		Avantage avantageUp = new Avantage();
		avantageUp.setId(avantageListe0.getId());
		avantageUp.setCode("code2");
		avantageUp.setNom("avantage2");
		avantageUp.setMontant(new BigDecimal("300.75"));
		// modifier un avantage
		avantageRepository.save(avantageUp);
		
		// vérifier que les modifications sont bien prises en compte via la méthode findOne
		Avantage avantageListe1 = avantageRepository.getByCode(avantageUp.getCode()).stream().filter(a -> a.getCode().equals(avantageUp.getCode())).findAny().orElse(new Avantage());
		assertThat(avantageListe1).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToIgnoringGivenFields(avantageUp, "id");
	}

}
