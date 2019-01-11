/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.model;

import java.util.ArrayList;

/**
 *
 * @author Alisson Solitto
 */
public class SensorModel {
    
    private String idSensor;
    private String typeName;
    private String label;
    private String cooment;
    private ArrayList<String> hasMeasurementCapability;
    private ArrayList<String> observes;
    private String hasLocation;
    
    public SensorModel(){
        this.hasMeasurementCapability = new ArrayList<String>();
        this.observes = new ArrayList<String>();
    }
    

    /**
     * @return the idSensor
     */
    public String getIdSensor() {
        return idSensor;
    }

    /**
     * @param idSensor the idSensor to set
     */
    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the cooment
     */
    public String getCooment() {
        return cooment;
    }

    /**
     * @param cooment the cooment to set
     */
    public void setCooment(String cooment) {
        this.cooment = cooment;
    }

    /**
     * @return the hasLocation
     */
    public String getHasLocation() {
        return hasLocation;
    }

    /**
     * @param hasLocation the hasLocation to set
     */
    public void setHasLocation(String hasLocation) {
        this.hasLocation = hasLocation;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return the hasMeasurementCapability
     */
    public ArrayList<String> getHasMeasurementCapability() {
        return hasMeasurementCapability;
    }

    /**
     * @param hasMeasurementCapability the hasMeasurementCapability to set
     */
    public void setHasMeasurementCapability(ArrayList<String> hasMeasurementCapability) {
        this.hasMeasurementCapability = hasMeasurementCapability;
    }

    /**
     * @return the observes
     */
    public ArrayList<String> getObserves() {
        return observes;
    }

    /**
     * @param observes the observes to set
     */
    public void setObserves(ArrayList<String> observes) {
        this.observes = observes;
    }
    
}
