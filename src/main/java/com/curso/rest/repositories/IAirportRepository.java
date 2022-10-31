package com.curso.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.rest.models.Airport;

public interface IAirportRepository extends JpaRepository<Airport, Long>{

}
