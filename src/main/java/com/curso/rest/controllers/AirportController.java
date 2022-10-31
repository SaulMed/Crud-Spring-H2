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
import com.curso.rest.models.Airport;
import com.curso.rest.services.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {

	@Autowired
	private AirportService airportService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Airport> lista = airportService.obtenerTodos();
		if(lista.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Sin Airports en la base de datos."), HttpStatus.BAD_REQUEST);
		}else {
			return ResponseEntity.ok().body(airportService.obtenerTodos());
		}
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Airport> getById(@PathVariable long Id) {
		return ResponseEntity.ok().body(airportService.obtenerUno(Id));
	}

	@PostMapping
	public ResponseEntity<Airport> save(@RequestBody Airport airport) {
		return ResponseEntity.ok().body(airportService.agregar(airport));
	}

	@PutMapping("/{Id}")
	public ResponseEntity<Airport> update(@RequestBody Airport airport, @PathVariable long Id) {
		airport.setId(Id);
		return ResponseEntity.ok().body(airportService.actualizar(airport));
	}

	@DeleteMapping("/{Id}")
	public HttpStatus delete(@PathVariable long Id) {
		airportService.eliminar(Id);
		return HttpStatus.OK;
	}

}
