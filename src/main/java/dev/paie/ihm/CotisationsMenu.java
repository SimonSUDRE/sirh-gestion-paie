package dev.paie.ihm;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.paie.entite.Cotisation;
import dev.paie.service.CotisationService;

@Component
public class CotisationsMenu {

	@Autowired
	private CotisationService cotisationService;
	
	@Autowired
	private Scanner scanner;
	
	/** LOG : Logger */
	public static final Logger CONSOLE = LoggerFactory.getLogger(CotisationsMenu.class);
	
	public void execute() {
		while(true) {
			CONSOLE.info("\n");
			CONSOLE.info("** Gestion des cotisations **");
			CONSOLE.info("1. Lister des cotisations");
			CONSOLE.info("2. Créer une cotisation");
			CONSOLE.info("3. Supprimer une cotisation");
			CONSOLE.info("99. Exit");
			String choix = scanner.next();
			if(choix.equals("1")) {
				CONSOLE.info("Liste des cotisations : ");
				List<Cotisation> cotisations = cotisationService.lister();
				CONSOLE.info("\n");
				for(Cotisation c : cotisations) {
					CONSOLE.info("Cotisation "+c.getId()+" : "+c.getCode()+", "+c.getLibelle()+" - Taux Patronal : "+c.getTauxPatronal()+" - Taux Salarial : "+c.getTauxSalarial());
				}
			}
			else if(choix.equals("2")) {
				CONSOLE.info("\n");
				Cotisation cotisation = new Cotisation();
				CONSOLE.info("Créer une cotisation : ");
				CONSOLE.info("code : ");
				choix = scanner.next();
				cotisation.setCode(choix);
				CONSOLE.info("libelle : ");
				choix = scanner.next();
				cotisation.setLibelle(choix);
				CONSOLE.info("taux patronal : ");
				choix = scanner.next();
				cotisation.setTauxPatronal(new BigDecimal(choix));
				CONSOLE.info("taux salarial : ");
				choix = scanner.next();
				cotisation.setTauxSalarial(new BigDecimal(choix));
				CONSOLE.info("\n");
				cotisationService.sauvegarder(cotisation);
			}
			else if(choix.equals("3")) {
				CONSOLE.info("\n");
				CONSOLE.info("supprimer une cotisation : ");
				CONSOLE.info("Liste des cotisations : ");
				List<Cotisation> cotisations = cotisationService.lister();
				CONSOLE.info("\n");
				for(Cotisation c : cotisations) {
					CONSOLE.info("Cotisation id : "+c.getId());
				}
				CONSOLE.info("\n");
				CONSOLE.info("\nChoisir un id de cotisation a supprimer : ");
				choix = scanner.next();
				Cotisation cotisation = new Cotisation();
				cotisation.setId(Integer.parseInt(choix));
				cotisationService.supprimer(cotisation);
			}
			else if(choix.equals("99")) {
				System.exit(0);
			}
		}
	}
}
