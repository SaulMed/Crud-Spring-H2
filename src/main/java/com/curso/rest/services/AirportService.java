package com.curso.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.rest.exceptions.ResourceNotFoundException;
import com.curso.rest.models.Airport;
import com.curso.rest.repositories.IAirportRepository;

@Service
public class AirportService {

	@Autowired
	private IAirportRepository airportRepository;

	public List<Airport> obtenerTodos() {
		return airportRepository.findAll();
	}

	public Airport agregar(Airport airport) {
		return airportRepository.save(airport);
	}

	public Airport actualizar(Airport airport) {
		Optional<Airport> data = airportRepository.findById(airport.getId());
		if (data.isPresent()) {
			Airport airportEdit = data.get();
			airportEdit.setName(airport.getName());
			airportRepository.save(airportEdit);
			return airportEdit;
		} else {
			throw new ResourceNotFoundException("Airport not found with id: " + airport.getId());
		}
	}

	public Airport obtenerUno(long Id) {
		Optional<Airport> data = airportRepository.findById(Id);
		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ResourceNotFoundException("Airport not found with id: " + Id);
		}
	}

	public void eliminar(long Id) {
		Optional<Airport> data = airportRepository.findById(Id);
		if (data.isPresent()) {
			airportRepository.deleteById(Id);
		} else {
			throw new ResourceNotFoundException("Airport not found with id: " + Id);
		}
	}

}
