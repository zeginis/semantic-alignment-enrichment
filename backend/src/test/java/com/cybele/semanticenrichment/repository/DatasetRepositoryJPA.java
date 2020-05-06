package com.cybele.semanticenrichment.repository;

import java.net.URI;
import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cybele.semanticenrichment.domain.Dataset;

public interface DatasetRepositoryJPA extends CrudRepository<Dataset, URI> { 
	
	List<Dataset> findByLabel(@Param("label") String label);

    List<Dataset> findByLanguage(@Param("language") String language);
	
}
