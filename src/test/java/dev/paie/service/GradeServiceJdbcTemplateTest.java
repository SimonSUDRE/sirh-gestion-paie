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
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class, DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
@Scope("test")
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;

	@Autowired
	@Qualifier("grade")
	private Grade nouveauGrade;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		nouveauGrade.setId(1);
		nouveauGrade.setCode("CODE1");

		gradeService.supprimer(nouveauGrade);
		
		// sauvegarder un nouveau grade
		gradeService.sauvegarder(nouveauGrade);

		// vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		Grade gradeliste0 = gradeService.lister().stream().filter(g -> g.getId() == 1).findAny().orElse(new Grade());
		assertThat(gradeliste0.getId()).isEqualTo(nouveauGrade.getId());
		assertThat(gradeliste0.getCode()).isEqualTo(nouveauGrade.getCode());
		assertThat(gradeliste0.getNbHeuresBase().compareTo(nouveauGrade.getNbHeuresBase())).isEqualTo(0);
		assertThat(gradeliste0.getTauxBase().compareTo(nouveauGrade.getTauxBase())).isEqualTo(0);
		
		// modifier un grade
		Grade gradeUp = new Grade();
		gradeUp.setId(1);
		gradeUp.setCode("Code2");
		gradeUp.setNbHeuresBase(new BigDecimal("115.76"));
		gradeUp.setTauxBase(new BigDecimal("12.9048"));
		gradeService.mettreAJour(gradeUp);

		// vérifier que les modifications sont bien prises en compte via la méthode lister
		Grade gradeliste1 = gradeService.lister().stream().filter(g -> g.getId() == 1).findAny().orElse(new Grade());
		assertThat(gradeliste1.getId()).isEqualTo(gradeUp.getId());
		assertThat(gradeliste1.getCode()).isEqualTo(gradeUp.getCode());
		assertThat(gradeliste1.getNbHeuresBase().compareTo(gradeUp.getNbHeuresBase())).isEqualTo(0);
		assertThat(gradeliste1.getTauxBase().compareTo(gradeUp.getTauxBase())).isEqualTo(0);


		// vérifier que la suppression est bien prise en compte
		gradeService.supprimer(gradeliste1);
		List<Grade> listeGrade = gradeService.lister();
		assertThat(listeGrade.size() == 0).isTrue();
	}
}
