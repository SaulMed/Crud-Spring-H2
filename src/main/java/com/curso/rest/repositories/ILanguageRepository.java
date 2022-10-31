package com.curso.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.rest.models.Language;

public interface ILanguageRepository extends JpaRepository<Language, Long>{

}
