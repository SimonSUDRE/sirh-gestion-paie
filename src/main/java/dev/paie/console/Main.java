package dev.paie.console;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.paie.config.ConsoleConfig;
import dev.paie.ihm.CotisationsMenu;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsoleConfig.class);
		CotisationsMenu cmenu = context.getBean(CotisationsMenu.class);
		cmenu.execute();
		context.close();
	}
}