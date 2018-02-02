package dev.paie.service;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sqlInsert = "INSERT INTO Grade(CODE, NBHEURESBASE, TAUXBASE) VALUES(?, ?, ?)";
		jdbcTemplate.update(sqlInsert, 
				nouveauGrade.getCode(), 
				nouveauGrade.getNbHeuresBase().toString(),
				nouveauGrade.getTauxBase().toString());
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sqlUpdate = "UPDATE Grade SET CODE = ?, NBHEURESBASE = ?, TAUXBASE = ? WHERE ID = ?";
		jdbcTemplate.update(sqlUpdate,  
				grade.getCode(), 
				grade.getNbHeuresBase().toString(),
				grade.getTauxBase().toString(),
				grade.getId());
	}
	
	@Override
	public void supprimer(Grade grade) {
		String sqlDelete = "DELETE FROM Grade WHERE CODE = ?";
		jdbcTemplate.update(sqlDelete, grade.getCode());
	}

	@Override
	public List<Grade> lister() {
		RowMapper<Grade> mapperGrade = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setId(rs.getInt("ID"));
			g.setCode(rs.getString("CODE"));
			g.setNbHeuresBase(rs.getBigDecimal("NBHEURESBASE"));
			g.setTauxBase(rs.getBigDecimal("TAUXBASE"));
			return g;
		};
		String sqlSelect = "SELECT * FROM Grade";
		return jdbcTemplate.query(sqlSelect, mapperGrade);
	}
}