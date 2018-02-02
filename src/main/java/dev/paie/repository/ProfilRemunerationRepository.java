package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.ProfilRemuneration;

public interface ProfilRemunerationRepository extends JpaRepository<ProfilRemuneration, Integer>  {

	public List<ProfilRemuneration> getByCode(String code);
}