package dev.paie.service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

@FunctionalInterface
public interface CalculerRemunerationService {
	ResultatCalculRemuneration calculer(BulletinSalaire	bulletin);
}
