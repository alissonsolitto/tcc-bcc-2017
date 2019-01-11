/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn.skeleton;

import com.framework.model.clsProperty;
import com.framework.ssn.OntologyFramework;
import com.framework.ssn.URI;
import java.util.ArrayList;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author Alisson Solitto
 */
public class SensorOutput {
    
    private final OntologyFramework OntologyFramework;
    private OntModel model;
    private String typeName;
    private ArrayList<clsProperty> lstProp;

    public SensorOutput(OntModel model) {
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.typeName = "SensorOutput";
        this.lstProp = new ArrayList<clsProperty>();
    }
    
    public void AddSensorOutput(String name) {
        try {
            OntologyFramework.AddIndividualSSN(this.model, name, this.typeName);
        } catch (Exception ex) {
            System.err.println("Erro no método AddSensorOutput => " + ex.getMessage());
        } 
    }
    
    public void AddSensorOutput(String name, String descComment, String descLabel) {
        try {
            OntologyFramework.AddIndividualSSN(this.model, name, this.typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddSensorOutput => " + ex.getMessage());
        } 
    }
    
    public void AddValuesSensorOutput(String indName, double dataValue, String symbol, String unit){
        
        String nameUnit = "Unit_" + indName;
        
        // ******************** Adicionando Unit ********************
        OntologyFramework.AddIndividualThing(this.model, nameUnit);
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasSymbol", symbol)); //C
        lstProp.add(new clsProperty(URI.ssn + "hasUnit", unit)); //Celsius
        
        OntologyFramework.AddPropertyIndividualSSN(this.model, nameUnit, lstProp);
        
        //Adicionando propriedades SensorOutput
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasValue", Double.toString(dataValue)));
        OntologyFramework.AddPropertyIndividualSSN(this.model, indName, lstProp);
        
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasClassifiedBy", URI.ssiot + nameUnit)); //Object Unit
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    
    
}
