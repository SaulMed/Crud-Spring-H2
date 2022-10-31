package com.curso.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.rest.exceptions.Mensaje;
import com.curso.rest.models.Country;
import com.curso.rest.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Country> data = countryService.obtenerTodos();
		if (data.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Sin Countries en la Base de Datos."), HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok().body(countryService.obtenerTodos());
		}
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Country> getById(@PathVariable long Id) {
		return ResponseEntity.ok().body(countryService.obtenerUno(Id));
	}

	@PostMapping
	public ResponseEntity<Country> save(@RequestBody Country country) {
		return ResponseEntity.ok().body(countryService.agregar(country));
	}

	@PutMapping("/{Id}")
	public ResponseEntity<Country> update(@RequestBody Country country, @PathVariable long Id) {
		country.setId(Id);
		return ResponseEntity.ok().body(countryService.actualizar(country));
	}

	@DeleteMapping("/{Id}")
	public HttpStatus delete(@PathVariable long Id) {
		countryService.eliminar(Id);
		return HttpStatus.OK;
	}

}
