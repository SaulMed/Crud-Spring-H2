package com.curso.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.rest.models.Country;

public interface ICountryRepository extends JpaRepository<Country,Long>{

}
