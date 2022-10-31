package com.curso.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.rest.exceptions.ResourceNotFoundException;
import com.curso.rest.models.Language;
import com.curso.rest.repositories.ILanguageRepository;

@Service
@Transactional
public class LanguageService{

	@Autowired
	private ILanguageRepository languageRepository;

	
	public List<Language> obtenerTodos() {
		return languageRepository.findAll();
	}

	
	public Language agregar(Language language) {
		return languageRepository.save(language);
	}

	
	public Language actualizar(Language language) {
		Optional<Language> data = this.languageRepository.findById(language.getId());
		if (data.isPresent()) {
			Language languageEdit = data.get();
			languageEdit.setCode(language.getCode());
			languageEdit.setName(language.getName());
			languageRepository.save(languageEdit);
			return languageEdit;
		} else {
			throw new ResourceNotFoundException("Language not found with id: " + language.getId());
		}
	}

	
	public Language obtenerUno(long languageId) {
		Optional<Language> data = this.languageRepository.findById(languageId);
		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ResourceNotFoundException("Language not found with id: " + languageId);
		}
	}

	
	public void eliminar(long languageId) {
		Optional<Language> data = this.languageRepository.findById(languageId);
		if (data.isPresent()) {
			this.languageRepository.deleteById(languageId);
		} else {
			throw new ResourceNotFoundException("Language not found with id: " + languageId);
		}
	}

}
