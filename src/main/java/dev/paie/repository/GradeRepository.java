package dev.paie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>  {

	public List<Grade> getByCode(String code);
}
