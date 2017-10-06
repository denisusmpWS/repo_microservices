package com.jdbc.template.app.demoAppContasisTemplate.service;

import java.util.List;

import com.jdbc.template.app.demoAppContasisTemplate.domain.Marca;

public interface MarcaService {

	public void insertarMarca(Marca marca);
	public void eliminarMarca(String id);
	public void actualizarMarca(Marca marca);
	
	public Marca consultarMarca(String id);
	public List<Marca> listarMarcas();
	
}
