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
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author Alisson Solitto
 */
public class MeasurementCapabilitySSN {
    
    private final OntologyFramework OntologyFramework;
    private OntModel model;    
    public String typeMeasurement;
    public String typeMeasuProperty;
    private ArrayList<clsProperty> lstProp;

    public MeasurementCapabilitySSN(OntModel model) {
        this.model = model;
        this.OntologyFramework = new OntologyFramework();
        this.lstProp = new ArrayList<clsProperty>();
        
        this.typeMeasurement = "MeasurementCapability";        
        this.model.createClass(URI.ssn + this.typeMeasurement);
    }
    
    public void AddMeasurementCapability(String indName){
        OntologyFramework.AddIndividualSSN(this.model, indName, this.typeMeasurement);
    }
    
    public void AddHasMeasurementProperty(String indName, String nameMeasurementResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasMeasurementProperty", URI.ssiot + nameMeasurementResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddForPropertyMeasurementCapability(String indName, String nameMeasurementResource){
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "forProperty", URI.ssiot + nameMeasurementResource));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddMeasurementProperty(String indName, String typeName){
        
        String nameMin = indName + "Min";
        String nameMax = indName + "Max";
        
        //Adicionando MeasurementProperty
        OntologyFramework.AddIndividualSSN(this.model, indName, typeName);
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasMeasurementPropertyMaxValue", URI.ssiot + nameMax));
        lstProp.add(new clsProperty(URI.ssn + "hasMeasurementPropertyMinValue", URI.ssiot + nameMin));
        
        OntologyFramework.AddResourceSSN(this.model, indName, lstProp);
    }
    
    public void AddMeasurementMinMax(String indName, String nameMinMax, double dataValue, String symbol, String unit){
        
        nameMinMax = indName + nameMinMax;
        String nameUnit = nameMinMax + "Unit";
        
        // ******************** Adicionando Unit ********************
        OntologyFramework.AddIndividualThing(this.model, nameUnit);
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasSymbol", symbol)); //C
        lstProp.add(new clsProperty(URI.ssn + "hasUnit", unit)); //Celsius
        
        OntologyFramework.AddPropertyIndividualSSN(this.model, nameUnit, lstProp);
        
        
        // ******************** Adicionando Min/Max ********************
        OntologyFramework.AddIndividualThing(this.model, nameMinMax);
        
        //Adicionando propriedades
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasValue", Double.toString(dataValue))); //25
        OntologyFramework.AddPropertyIndividualSSN(this.model, nameMinMax, lstProp);
        
        lstProp.clear();
        lstProp.add(new clsProperty(URI.ssn + "hasClassifiedBy", URI.ssiot + nameUnit)); //Object Unit
        OntologyFramework.AddResourceSSN(this.model, nameMinMax, lstProp);
    }
    
    
    
    
}
