/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn.skeleton;

import com.framework.model.clsListType;
import com.framework.model.clsProperty;
import com.framework.ssn.OntologyFramework;
import com.framework.ssn.URI;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

/**
 *
 * @author Alisson Solitto
 */
public class SpatialThingSSN {

    private final OntologyFramework OntologyFramework;
    private OntModel model;
    private String typePoint;
    private ArrayList<clsProperty> lstProp;

    public SpatialThingSSN(OntModel model) {
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.lstProp = new ArrayList<clsProperty>();
        this.typePoint = "Point";
    }

    public void AddPoint(String indName, String descLabel, String descComment, float lat, float lon, float alt) {
        
        OntClass tipo = this.model.getOntClass(URI.geo + this.typePoint);
        Individual space = this.model.createIndividual(URI.ssiot + indName, tipo); 
        
        //Comentário e label
        space.addComment(descComment, "PT-BR");
        space.addLabel(descLabel, "PT-BR");
        
        //Limpando lista
        lstProp.clear();
        //Adicionando propriedades
        lstProp.add(new clsProperty(URI.geo + "lat", Float.toString(lat)));
        lstProp.add(new clsProperty(URI.geo + "long", Float.toString(lon)));
        lstProp.add(new clsProperty(URI.geo + "alt", Float.toString(alt)));
        
        OntologyFramework.AddPropertyIndividualSSN(this.model, indName, lstProp);
    }
    
    public void DeletePoint(String indName) {
        OntClass type = this.model.getOntClass(URI.geo + this.typePoint);
        Individual cls = this.model.getIndividual(URI.ssiot + indName);
        
        if (type.equals(cls.getOntClass())) {
            cls.remove();
        }
    }
    
     public ArrayList<clsListType> ListPoint() {

        model.setStrictMode(false);
        OntClass clsType = model.getOntClass(URI.geo + this.typePoint);
        ArrayList<clsListType> lstResult = new ArrayList<clsListType>();

        for (Iterator i = clsType.listInstances(); i.hasNext();) {

            Individual c = (Individual) i.next();

            if (c.getURI().contains(URI.ssiot)) {

                lstResult.add(new clsListType(c.getURI(),
                        c.getLocalName(),
                        clsType.getLocalName(),
                        c.getLabel("PT-BR"),
                        c.getComment("PT-BR")));
            }

        }

        return lstResult;
    }
}
