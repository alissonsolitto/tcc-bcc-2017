/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn.skeleton;

import com.framework.model.clsListType;
import com.framework.ssn.OntologyFramework;
import com.framework.ssn.URI;
import java.util.ArrayList;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

/**
 *
 * @author Alisson Solitto
 */
public class SensingDeviceSSN {

    private final OntologyFramework OntologyFramework;
    private String typeName;
    private OntModel model;

    public SensingDeviceSSN(OntModel model){
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.typeName = "SensingDevice";
        
        this.model.createClass(URI.ssn + typeName);
    }
    
    public void AddType(String className, String descLabel, String descComment) {
        try {
            OntologyFramework.AddTypeSensorSSN(this.model, className, this.typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddType => " + ex.getMessage());
        }
    }
        
    public ArrayList<clsListType> List(){
        try {
            return OntologyFramework.ListType(this.model, this.typeName);
        } catch (Exception ex) {
            throw new Error("Problema na listagem de classes");         
        }
    }
    
    public void Delete(String className){
        try {
            OntologyFramework.DeleteTypeSSN(this.model, className,this.typeName);
        } catch (Exception ex) {
            System.err.println("Erro no método Delete => " + ex.getMessage());
        }
    }
    
    

}
