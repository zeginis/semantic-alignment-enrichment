package com.cybele.semanticenrichment.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cybele.semanticenrichment.domain.Dataset;
import com.cybele.semanticenrichment.domain.SKOSConcept;
import com.cybele.semanticenrichment.repository.DatasetRepository;
import com.cybele.semanticenrichment.util.DatasetUtils;


@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);
            
    @PostMapping(path = "/dataset")
    @ResponseStatus(HttpStatus.CREATED)
    public URI addNewDataset (@RequestBody Dataset dataset) {
    	dataset.setUri(DatasetUtils.randomURI("dataset"));
    	DatasetRepository dtr=new DatasetRepository();
    	Dataset insertedDataset= dtr.insertDataset(dataset);
	    LOG.info(insertedDataset.toString() + " successfully saved into DB");
	    return insertedDataset.getUri();
    }

    @GetMapping(path = "/codelist/{id}")
    public List<SKOSConcept> getCodelist(@PathVariable("id") String id) {
    	DatasetRepository dtr=new DatasetRepository();
    	List<SKOSConcept> list=dtr.getCodelistContent(id);
        return list;
    }
  

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }

}
