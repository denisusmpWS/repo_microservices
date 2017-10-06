package com.jdbc.template.app.demoAppContasisTemplate.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.jdbc.template.app.demoAppContasisTemplate.domain.Marca;

@Repository
public class MarcaDAOImpl implements MarcaDAO{
	
	@Autowired
	private JdbcTemplate JDBCtemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.JDBCtemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void insertarMarca(Marca marca) {
		this.JDBCtemplate.update("insert into cc_marca(ccodmar,cdesmar) values(?,?)",
				marca.getCcodmar(),marca.getCdesmar());
	}

	@Override
	public void eliminarMarca(String id) {
		this.JDBCtemplate.update("delete from cc_marca where ccodmar=?", id);
	}

	@Override
	public void actualizarMarca(Marca marca) {
		this.JDBCtemplate.update("update cc_marca set ccodmar=?,cdesmar=? where ccodmar=?",
				marca.getCcodmar(),marca.getCdesmar(),marca.getCcodmar());
		}

	@Override
	public Marca consultarMarca(String id) {
		return JDBCtemplate.queryForObject("select * from cc_marca where ccodmar=?", 
				new BeanPropertyRowMapper<Marca>(Marca.class),id);
	}

	@Override
	public List<Marca> listarMarcas() {
		return this.JDBCtemplate.query("select * from cc_marca", 
				new BeanPropertyRowMapper<Marca>(Marca.class));
	}

	
	
}
