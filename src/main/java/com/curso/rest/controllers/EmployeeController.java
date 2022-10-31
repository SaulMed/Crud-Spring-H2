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
import com.curso.rest.models.Employee;
import com.curso.rest.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Employee> lista = employeeService.obtenerTodos();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Sin Employees en la Base de Datos"), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(employeeService.obtenerTodos());
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Employee> getById(@PathVariable long Id) {
		return ResponseEntity.ok().body(employeeService.obtenerUno(Id));
	}

	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		return ResponseEntity.ok().body(employeeService.agregar(employee));
	}

	@PutMapping("/{Id}")
	public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable long Id) {
		employee.setId(Id);
		return ResponseEntity.ok().body(employeeService.actualizar(employee));
	}

	@DeleteMapping("/{Id}")
	public HttpStatus delete(@PathVariable long Id) {
		this.employeeService.eliminar(Id);
		return HttpStatus.OK;
	}

}
