/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn;

import java.io.InputStream;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 *
 * @author Alisson Solitto
 */
public class LoadOntology {
    
    public static Boolean ValidityOntology(OntModel model) {
        Boolean checkValidity = false;
        InfModel infmodel = ModelFactory.createRDFSModel(model);

        if (infmodel.validate().isValid()) {
            checkValidity = true;
        }

        return checkValidity;
    }

    public static OntModel Load(String filename, OntModelSpec typeStore) {

        // Criando modelo vazio
        OntModel model = ModelFactory.createOntologyModel(typeStore);

        // Carregando arquivo
        InputStream in = FileManager.get().open(filename);

        if (in == null) {
            throw new IllegalArgumentException("Arquivo: " + filename + " inválido!");
        }

        //Verifica se a ontologia é valida
        if (ValidityOntology(model)) {
            // Carregando o arquivo para o modelo
            model.read(in, null);
        }

        return model;
    }
    
}
