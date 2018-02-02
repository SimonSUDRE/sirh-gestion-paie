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
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { GradeServiceJdbcTemplate.class, JeuxDeDonneesConfig.class, DataSourceMySQLConfig.class })
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
		nouveauGrade.setCode("CODE1");
		
		// sauvegarder un nouveau grade
		gradeService.sauvegarder(nouveauGrade);

		// vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		Grade gradeliste0 = gradeService.lister().stream().filter(g -> g.getCode().equals(nouveauGrade.getCode())).findAny().orElse(new Grade());
		assertThat(gradeliste0).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToIgnoringGivenFields(nouveauGrade, "id");
		
		// modifier un grade
		Grade gradeUp = new Grade();
		gradeUp.setId(gradeliste0.getId());
		gradeUp.setCode("Code2");
		gradeUp.setNbHeuresBase(new BigDecimal("115.76"));
		gradeUp.setTauxBase(new BigDecimal("12.9048"));
		gradeService.mettreAJour(gradeUp);

		// vérifier que les modifications sont bien prises en compte via la méthode lister
		Grade gradeliste1 = gradeService.lister().stream().filter(g -> g.getCode().equals(gradeUp.getCode())).findAny().orElse(new Grade());
		assertThat(gradeliste1).usingComparatorForType((a,b) -> {
			if(a != null)
				return a.compareTo(b);
			else
				return -1;
		}, BigDecimal.class).isEqualToIgnoringGivenFields(gradeUp, "id");


		// vérifier que la suppression est bien prise en compte via la méthode lister
		gradeService.supprimer(gradeliste1);
		List<Grade> listeGrade = gradeService.lister();
		assertThat(listeGrade.size() == 0).isTrue();
	}
}
