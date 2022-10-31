package com.curso.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.rest.exceptions.ResourceNotFoundException;
import com.curso.rest.models.Employee;
import com.curso.rest.repositories.IEmployeeRepository;

@Service
@Transactional
public class EmployeeService{

	@Autowired
	private IEmployeeRepository employeeRepository;

	
	public List<Employee> obtenerTodos() {
		return employeeRepository.findAll();
	}

	
	public Employee agregar(Employee employee) {
		return employeeRepository.save(employee);
	}


	public Employee actualizar(Employee employee) {
		Optional<Employee> data = this.employeeRepository.findById(employee.getId());
		if (data.isPresent()) {
			Employee employeeEdit = data.get();
			employeeEdit.setName(employee.getName());
			employeeEdit.setSurname(employee.getSurname());
			employeeRepository.save(employeeEdit);
			return employeeEdit;
		} else {
			throw new ResourceNotFoundException("Employee not found with id: " + employee.getId());
		}
	}

	
	public Employee obtenerUno(long employeeId) {
		Optional<Employee> data = this.employeeRepository.findById(employeeId);
		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
		}
	}

	
	public void eliminar(long employeeId) {
		Optional<Employee> data = this.employeeRepository.findById(employeeId);
		if (data.isPresent()) {
			this.employeeRepository.deleteById(employeeId);
		} else {
			throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
		}
	}

}
