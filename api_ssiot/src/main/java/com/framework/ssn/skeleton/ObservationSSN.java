/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn.skeleton;

import com.framework.model.clsProperty;
import com.framework.ssn.OntologyFramework;
import com.framework.ssn.URI;
import com.framework.ssn.Uteis;
import java.util.ArrayList;
import java.util.Date;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author Alisson Solitto
 */
public class ObservationSSN {
    
    private final OntologyFramework OntologyFramework;
    private OntModel model;
    private String typeName;
    private ArrayList<clsProperty> lstProp;

    public ObservationSSN(OntModel model) {
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.typeName = "Observation";
        this.lstProp = new ArrayList<clsProperty>();
    }

    public void AddObservation(String name, String descComment, String descLabel) {
        try {
            OntologyFramework.AddIndividualSSN(this.model, name, this.typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddObservation => " + ex.getMessage());
        } 
    }
    
    public void AddObservedProperty(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "observedProperty", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddFeatureOfInterest(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "featureOfInterest", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddObservedBy(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "observedBy", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddObservationResult(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "observationResult", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddObservationResultTime(String indName){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "observationResultTime", Uteis.getDateTime()));
        
        OntologyFramework.AddPropertyIndividualSSN(this.model, indName, lstProp);
    }
}
