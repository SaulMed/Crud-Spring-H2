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
import com.curso.rest.models.Language;
import com.curso.rest.services.LanguageService;

@RestController
@RequestMapping("/languages")
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Language> lista = languageService.obtenerTodos();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Sin Languages en la Base de Datos."), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(languageService.obtenerTodos());
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Language> getById(@PathVariable long Id) {
		return ResponseEntity.ok().body(languageService.obtenerUno(Id));
	}

	@PostMapping
	public ResponseEntity<Language> save(@RequestBody Language language) {
		return ResponseEntity.ok().body(languageService.agregar(language));
	}

	@PutMapping("/{Id}")
	public ResponseEntity<Language> update(@RequestBody Language language, @PathVariable long Id) {
		language.setId(Id);
		return ResponseEntity.ok().body(languageService.actualizar(language));
	}

	@DeleteMapping("/{Id}")
	public HttpStatus delete(@PathVariable long Id) {
		languageService.eliminar(Id);
		return HttpStatus.OK;
	}

}
