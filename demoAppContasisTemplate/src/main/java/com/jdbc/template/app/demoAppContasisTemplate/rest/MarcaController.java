package com.jdbc.template.app.demoAppContasisTemplate.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbc.template.app.demoAppContasisTemplate.domain.Marca;
import com.jdbc.template.app.demoAppContasisTemplate.service.MarcaService;

@RestController
@RequestMapping(value="/marca/api2",produces=MediaType.APPLICATION_JSON_VALUE)
public class MarcaController {
	
	final static Logger LOG=LoggerFactory.getLogger(MarcaController.class);
	
	@Autowired
	private MarcaService service;
	
	@RequestMapping(value="/search/{id}",method=RequestMethod.GET)
	public ResponseEntity<Marca> consultarMarca(@PathVariable("id") String id) throws JsonProcessingException{
		
		Marca marca=new Marca();
		int cod;
		
		try {
			cod=Integer.parseInt(id);
		}catch(NumberFormatException a) {
			LOG.error("STATUS: "+HttpStatus.NOT_ACCEPTABLE);
			return new  ResponseEntity<Marca>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
		marca=service.consultarMarca(id);		
		}catch(Exception e){
			LOG.error("STATUS: "+HttpStatus.NOT_FOUND);
			return new ResponseEntity<Marca>(HttpStatus.NOT_FOUND);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String convertJson=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marca).toString();	
		LOG.info("Invocked Method 'ConsultarMarca()': /marca/api2/search/"+id);
		LOG.info("RESPONSE: "+convertJson);
		LOG.info("STATUS: "+HttpStatus.OK);
		return new ResponseEntity<Marca>(marca,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<Void> grabarMarca(@RequestBody Marca marca,UriComponentsBuilder builder) throws JsonProcessingException{
		LOG.info("Invoked method 'actualizarMarca()': /marca/api2/add/");
		service.insertarMarca(marca);
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(builder.path("/marca/api2/search/{id}").buildAndExpand(marca.getCcodmar()).toUri());	
		
		ObjectMapper mapper=new ObjectMapper();
		String convert=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marca).toString();
		LOG.info("PAYLOAD: "+convert);
		LOG.info("STATUS: "+HttpStatus.CREATED);
		LOG.info("HEADERS: "+headers.getLocation());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Marca> actualizarMarca(@RequestBody Marca marca, @PathVariable("id") String id){

		Marca marca2=new Marca();
		int cod;
		
		try {
			cod=Integer.parseInt(id);
		}catch(NumberFormatException e){
			LOG.info(""+HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<Marca>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			marca2=service.consultarMarca(id);
		}catch(Exception e) {
			return new ResponseEntity<Marca>(HttpStatus.NOT_FOUND);
		}

		
		if(!marca.getCcodmar().equals(id)) {
			return new ResponseEntity<Marca>(HttpStatus.CONFLICT);
		}
	
		service.actualizarMarca(marca);			
		return new ResponseEntity<Marca>(marca,HttpStatus.OK);
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<Marca>> listarMarca() throws JsonProcessingException{
		LOG.info("Invoked Method 'listarMarcas()': /marca/api2/all");
		List<Marca> marcas=new ArrayList<Marca>();
		marcas=service.listarMarcas();
		if(marcas.isEmpty()) {
			LOG.error("STATUS: "+HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<Marca>>(HttpStatus.NO_CONTENT);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String response=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marcas).toString();
		LOG.info("RESPONSE: " + response);
		LOG.info("STATUS: "+HttpStatus.OK);
		return new ResponseEntity<List<Marca>>(marcas,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarMarca(@PathVariable("id") String id){
		
		Marca marca=new Marca();
		int cod;
		
		try {
			cod=Integer.parseInt(id);
		}catch(NumberFormatException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		marca=service.consultarMarca(id);
		if(marca==null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		service.eliminarMarca(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
