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
public class PropertySSN {

    private final OntologyFramework OntologyFramework;
    private String typeName;
    private OntModel model;
   
    public PropertySSN(OntModel model){
        this.model = model;        
        this.OntologyFramework = new OntologyFramework();
        this.typeName = "Property";
        
        this.model.createClass(URI.ssn + typeName);
    }
        
    public void AddType(String className, String descLabel, String descComment) {
        try {
            OntologyFramework.AddTypeSSN(this.model, className, this.typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddType => " + ex.getMessage());
        }
    }
    
    public void AddType(String className) {
        try {
            OntologyFramework.AddTypeSSN(this.model, className, this.typeName);
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
    
    public void AddPropertyInd(String name, String descComment, String descLabel){
        try {
            OntologyFramework.AddIndividualSSN(this.model, name, this.typeName, descComment, descLabel);
        } catch (Exception ex) {
            System.err.println("Erro no método AddPropertyInd => " + ex.getMessage());
        }        
    }
    
    public ArrayList<clsListType> ListInd(){
        try {
            return OntologyFramework.ListIndividual(this.model, this.typeName);
        } catch (Exception ex) {
            throw new Error("Problema na listagem de instâncias");
        }
    }
    
    public void DeleteInd(String indName){
        try {
            OntologyFramework.DeleteIndividualSSN(this.model, indName, this.typeName);
        } catch (Exception ex) {
            System.err.println("Erro no método Delete => " + ex.getMessage());
        }
    }
}
