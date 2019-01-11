/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn;

import com.framework.model.clsListSensorType;
import com.framework.model.clsListType;
import com.framework.model.clsProperty;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author Alisson Solitto
 */
public class OntologyFramework {

    private String language = "PT-BR";

    /**
     * ************************************************************************
     * MÉTODOS PARA MANIPULAR CLASSES
     * *************************************************************************
     */
    public void AddTypeSensorSSN(OntModel model, String className, String typeName, String descComment, String descLabel) {
        OntClass cls = model.createClass(URI.ssiot + className);
        model.getOntClass(URI.ssiot + typeName).addSubClass(cls);

        //Propriedades
        AddLabelComment(cls, descComment, descLabel);
    }
    
    public void AddTypeSSN(OntModel model, String className, String typeName, String descComment, String descLabel) {
        OntClass cls = model.createClass(URI.ssiot + className);
        model.getOntClass(URI.ssn + typeName).addSubClass(cls);

        //Propriedades
        AddLabelComment(cls, descComment, descLabel);
    }

    public void AddTypeSSN(OntModel model, String className, String typeName) {
        OntClass cls = model.createClass(URI.ssiot + className);
        model.getOntClass(URI.ssn + typeName).addSubClass(cls);
    }

    public void AddLabelComment(OntClass cls, String descComment, String descLabel) {
        cls.addComment(descComment, this.language);
        cls.addLabel(descLabel, this.language);
    }

    public void AddLabelComment(Individual ind, String descComment, String descLabel) {
        ind.addComment(descComment, this.language);
        ind.addLabel(descLabel, this.language);
    }

    public void DeleteTypeSSN(OntModel model, String className, String typeName) {
        OntClass type = model.getOntClass(URI.ssn + typeName);
        OntClass cls = model.getOntClass(URI.ssiot + className);

        if (type.hasSubClass(cls)) {
            cls.remove();
        }
    }

    public ArrayList<clsListType> ListType(OntModel model, String typeName) {

        model.setStrictMode(false);
        OntClass clsType = model.getOntClass(URI.ssn + typeName);
        ArrayList<clsListType> lstResult = new ArrayList<clsListType>();

        for (Iterator i = clsType.listSubClasses(); i.hasNext();) {

            OntClass c = (OntClass) i.next();

            if (c.getURI().contains(URI.ssiot)) {

                lstResult.add(new clsListType(c.getURI(),
                        c.getLocalName(),
                        clsType.getLocalName(),
                        c.getLabel(this.language),
                        c.getComment(this.language)));

            }

        }

        return lstResult;
    }

    /**
     * ************************************************************************
     * MÉTODOS PARA MANIPULAR INSTANCIAS
     * *************************************************************************
     */
    public void AddIndividualSSN(OntModel model, String indName, String typeName) {

        OntClass tipo = model.getOntClass(URI.ssn + typeName);

        if (tipo == null) {
            model.createClass(URI.ssn + typeName);
            tipo = model.getOntClass(URI.ssn + typeName);
        }

        model.createIndividual(URI.ssiot + indName, tipo);
    }

    public void AddIndividualSSN(OntModel model, String indName, String typeName, String descComment, String descLabel) {

        OntClass tipo = model.getOntClass(URI.ssn + typeName);

        if (tipo == null) {
            model.createClass(URI.ssn + typeName);
            tipo = model.getOntClass(URI.ssn + typeName);
        }

        Individual ind = model.createIndividual(URI.ssiot + indName, tipo);
        ind.addLabel(descLabel, this.language);
        ind.addComment(descComment, this.language);
    }
    
    public void AddIndividualSensorSSN(OntModel model, String indName, String typeName, String descComment, String descLabel) {

        OntClass tipo = model.getOntClass(URI.ssiot + typeName);

        if (tipo == null) {
            model.createClass(URI.ssn + typeName);
            tipo = model.getOntClass(URI.ssn + typeName);
        }

        Individual ind = model.createIndividual(URI.ssiot + indName, tipo);
        ind.addLabel(descLabel, this.language);
        ind.addComment(descComment, this.language);
    }

    public void AddIndividualThing(OntModel model, String indName) {

        Resource tipo = model.getResource(URI.owl + "Thing");
        model.createIndividual(URI.ssiot + indName, tipo);
    }

    public void AddPropertyIndividualSSN(OntModel model, String indName, ArrayList<clsProperty> prop) {

        Individual ind = model.getIndividual(URI.ssiot + indName);

        for (clsProperty p : prop) {
            ind.addProperty(model.createDatatypeProperty(p.getNameProperty()), p.getValueProperty());
        };
    }

    public void AddResourceSSN(OntModel model, String indName, ArrayList<clsProperty> prop) {

        Individual ind = model.getIndividual(URI.ssiot + indName);

        for (clsProperty p : prop) {
            ind.addProperty(model.createObjectProperty(p.getNameProperty()), model.createResource(p.getValueProperty()));
        };
    }

    public void DeleteIndividualSSN(OntModel model, String indName, String typeName) {
        OntClass type = model.getOntClass(URI.ssn + typeName);
        Individual cls = model.getIndividual(URI.ssiot + indName);

        if (cls.hasOntClass(type)) {
            cls.remove();
        }
    }

    public ArrayList<clsListType> ListIndividual(OntModel model, String typeName) {

        model.setStrictMode(false);
        OntClass clsType = model.getOntClass(URI.ssn + typeName);
        ArrayList<clsListType> lstResult = new ArrayList<clsListType>();

        for (Iterator i = clsType.listInstances(); i.hasNext();) {

            Individual c = (Individual) i.next();

            if (c.getURI().contains(URI.ssiot)) {

                lstResult.add(new clsListType(c.getURI(),
                        c.getLocalName(),
                        clsType.getLocalName(),
                        c.getLabel(this.language),
                        c.getComment(this.language)));

            }

        }

        return lstResult;
    }

    public ArrayList<clsListSensorType> ListIndividualSensor(OntModel model, String typeName) {

        model.setStrictMode(false);
        OntClass clsType = model.getOntClass(URI.ssiot + typeName);
        ArrayList<clsListSensorType> lstResult = new ArrayList<clsListSensorType>();

        for (Iterator i = clsType.listInstances(); i.hasNext();) {

            Individual c = (Individual) i.next();

            if (c.getURI().contains(URI.ssiot)) {

                String prop = c.getPropertyResourceValue(model.getObjectProperty(URI.ssn + "observes")).getLocalName();

                lstResult.add(new clsListSensorType(c.getURI(),
                        c.getLocalName(),
                        clsType.getLocalName(),
                        c.getLabel(this.language),
                        c.getComment(this.language),
                        prop));
            }

        }

        return lstResult;
    }
}
