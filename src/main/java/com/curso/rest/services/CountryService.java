package com.curso.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.rest.exceptions.ResourceNotFoundException;
import com.curso.rest.models.Country;
import com.curso.rest.repositories.ICountryRepository;

@Service
public class CountryService{

	@Autowired
	private ICountryRepository countryRepository;

	public List<Country> obtenerTodos() {
		return countryRepository.findAll();
	}

	
	public Country agregar(Country country) {
		return countryRepository.save(country);
	}

	
	public Country actualizar(Country country) {
		Optional<Country> data = countryRepository.findById(country.getId());
		if (data.isPresent()) {
			Country countryEdit = data.get();
			countryEdit.setCode(country.getCode());
			countryEdit.setName(country.getName());
			countryRepository.save(countryEdit);
			return countryEdit;
		} else {
			throw new ResourceNotFoundException("Country not found with id: " + country.getId());
		}
	}

	
	public Country obtenerUno(long Id) {
		Optional<Country> data = countryRepository.findById(Id);
		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ResourceNotFoundException("Country not found with id: " + Id);
		}
	}

	
	public void eliminar(long Id) {
		Optional<Country> data = countryRepository.findById(Id);
		if (data.isPresent()) {
			countryRepository.deleteById(Id);
		} else {
			throw new ResourceNotFoundException("Country not found with id: " + Id);
		}
	}

}
