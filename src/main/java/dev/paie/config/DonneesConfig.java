package dev.paie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
	"classpath:cotisations-imposables.xml",
	"classpath:cotisations-non-imposables.xml",
	"classpath:entreprises.xml",
	"classpath:grades.xml",
	"classpath:profils-remuneration.xml",
	"classpath:utilisateurs.xml"
	})
public class DonneesConfig {

}
