package com.jdbc.template.app.demoAppContasisTemplate.dao;

import java.util.List;

import com.jdbc.template.app.demoAppContasisTemplate.domain.Marca;

public interface MarcaDAO {

	
	public void insertarMarca(Marca marca);
	public void eliminarMarca(String id);
	public void actualizarMarca(Marca marca);
	
	public Marca consultarMarca(String id);
	public List<Marca> listarMarcas();
	
}
