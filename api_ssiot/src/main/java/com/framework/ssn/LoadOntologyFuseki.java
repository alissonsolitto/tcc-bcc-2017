/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn;

import static com.framework.ssn.LoadOntology.ValidityOntology;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

/**
 *
 * @author Alisson Solitto
 */
public class LoadOntologyFuseki {

    public static String ontUri = "http://localhost:3030/ssn_fuseki/";
    public static DatasetAccessor accessor;
    public static OntModel modelSuper;

    public static Boolean ValidityOntology(OntModel model) {

        Boolean checkValidity = false;
        InfModel infmodel = ModelFactory.createRDFSModel(model);

        if (infmodel.validate().isValid()) {
            checkValidity = true;
        }

        return checkValidity;
    }

    public static OntModel Load() {

        OntModel model;

        if (modelSuper == null) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            //Carregando ontologia fuseki
            accessor = DatasetAccessorFactory.createHTTP(ontUri);

            //Salvando no buffer
            accessor.getModel().write(buffer);
            //Carregando o buffer no inputStream
            InputStream inputStream = new ByteArrayInputStream(buffer.toByteArray());

            //Criando OntModel Jena e carregando inputStream
            model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

            //Verifica se a ontologia é valida
            if (ValidityOntology(model)) {
                // Carregando o arquivo para o modelo
                model.read(inputStream, null);
            }

            modelSuper = model;
        } else {
            model = modelSuper;
        }

        return model;
    }

    public static void setModel(OntModel model) {

        Model m = ModelFactory.createDefaultModel();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        model.write(buffer);

        InputStream in = new ByteArrayInputStream(buffer.toByteArray());
        m.read(in, null, "RDF/XML");

        accessor.putModel(m);
    }

}
