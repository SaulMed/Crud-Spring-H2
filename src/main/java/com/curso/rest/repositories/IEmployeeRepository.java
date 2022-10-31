package com.curso.rest.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.rest.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>{

}
