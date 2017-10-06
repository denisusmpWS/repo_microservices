package com.jdbc.template.app.demoAppContasisTemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdbc.template.app.demoAppContasisTemplate.dao.MarcaDAO;
import com.jdbc.template.app.demoAppContasisTemplate.domain.Marca;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaDAO dao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insertarMarca(Marca marca) {
		this.dao.insertarMarca(marca);
		
	}

	@Override
	public void eliminarMarca(String id) {
		this.dao.eliminarMarca(id);
	}

	@Override
	public void actualizarMarca(Marca marca) {
		this.dao.actualizarMarca(marca);
	}

	@Override
	public Marca consultarMarca(String id) {
		return this.dao.consultarMarca(id);
	}

	@Override
	public List<Marca> listarMarcas() {
		return this.dao.listarMarcas();
	}

}
