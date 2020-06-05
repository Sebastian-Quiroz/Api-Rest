/**
 * 
 */
package com.sebas.quiroz.quiroz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sebas.quiroz.quiroz.model.Person;
import com.sebas.quiroz.quiroz.service.PersonService;

/**
 * @author ADMIN
 *
 */
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/clientes")
	public List<Person> index() {
		return personService.list();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Person person = null;
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		try {
			person = personService.personId(id);
		} catch (DataAccessException e) {
			respoMap.put("mensaje", "Error al realizar la consulta a la base de datos");
			respoMap.put("error", e.getMessage()+": ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String,Object> >(respoMap,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (person != null) {
			return new ResponseEntity<Person>(person,HttpStatus.OK);
		} else {
			respoMap.put("mensaje", "El cliente: "+id+" no existe en la base de datos");
			return new ResponseEntity< Map<String,Object> >(respoMap,HttpStatus.NOT_FOUND);
		}
		
		
		
		
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Person person) {
		
		Person personNew = null;
		person.setBirth(new Date());
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		try {
				personNew = personService.save(person);
		} catch (DataAccessException e) {
			respoMap.put("mensaje", "Error al realizar en insert");
			respoMap.put("error", "Error del insert : "+e.getMessage()+" : "+e.getMostSpecificCause());
			
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respoMap.put("mensaje", "Cliente creado con exito!");
		respoMap.put("cliente", personNew);
		return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Person person, @PathVariable Long id) {
		
		// Obtener el cliente que se va actualizar
		Person personAct = personService.personId(id);
		Person personUpdated = null;
		
		Map<String, Object> respoMap = new HashMap<String, Object>();
		
		if (personAct == null) {
			respoMap.put("Mensaje", "Cliente no encontrado, el ID "+id+" no existe en la base de datos");
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.NOT_FOUND);
		}
		try {
			// Actualizar el persona con los nuevos valores que llegan
			personAct.setFullName(person.getFullName());
			personUpdated = personService.save(personAct);
		} catch (DataAccessException e) {
			respoMap.put("error", "error: "+e.getMessage()+" DETALLES: "+e.getMostSpecificCause().toString());
			return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		respoMap.put("mensaje", "Persona Actualizado");
		respoMap.put("cliente:",personUpdated);
		return new ResponseEntity< Map<String, Object> >(respoMap, HttpStatus.CREATED);
		
	}
	
	
}
