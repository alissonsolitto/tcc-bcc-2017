/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn.skeleton;

import com.framework.model.clsListSensorType;
import com.framework.model.clsListType;
import com.framework.model.clsProperty;
import com.framework.ssn.OntologyFramework;
import com.framework.ssn.URI;
import java.util.ArrayList;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author Alisson Solitto
 */
public class SensorSSN {
    
    private final OntologyFramework OntologyFramework;
    private OntModel model;
    private ArrayList<clsProperty> lstProp;

    public SensorSSN(OntModel model) {
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.lstProp = new ArrayList<clsProperty>();
    }

    public void AddSensor(String name, String typeName, String descComment, String descLabel) {
        try {
            OntologyFramework.AddIndividualSensorSSN(this.model, name, typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddSensor => " + ex.getMessage());
        }
    }
    
    public void AddHasMeasurementCapability(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasMeasurementCapability", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddObserves(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "observes", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddHasLocation(String indName, String nameResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasLocation", URI.ssiot + nameResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }    
    
    public ArrayList<clsListType> List(String typeName){
        try {
            return OntologyFramework.ListIndividual(this.model, typeName);
        } catch (Exception ex) {
            throw new Error("Problema na listagem de instâncias");
        }
    }
    
    public ArrayList<clsListSensorType> ListAll(String typeName){
        try {
            return OntologyFramework.ListIndividualSensor(this.model, typeName);
        } catch (Exception ex) {
            throw new Error("Problema na listagem de instâncias");
        }
    }
    
    public void Delete(String indName, String typeName){
        try {
            OntologyFramework.DeleteIndividualSSN(this.model, indName, typeName);
        } catch (Exception ex) {
            System.err.println("Erro no método Delete => " + ex.getMessage());
        }
    }
    
    
}
