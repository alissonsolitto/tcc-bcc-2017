/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.model;

/**
 *
 * @author Alisson Solitto
 */
public class ObservationModel {
    
    private String nameSensor;
    private String descComment;
    private String descLabel;
    private String observedProperty;
    private String featureOfInterest;
    private int value;
    private String symbol;
    private String unit;

    /**
     * @return the nameSensor
     */
    public String getNameSensor() {
        return nameSensor;
    }

    /**
     * @param nameSensor the nameSensor to set
     */
    public void setNameSensor(String nameSensor) {
        this.nameSensor = nameSensor;
    }

    /**
     * @return the descComment
     */
    public String getDescComment() {
        return descComment;
    }

    /**
     * @param descComment the descComment to set
     */
    public void setDescComment(String descComment) {
        this.descComment = descComment;
    }

    /**
     * @return the descLabel
     */
    public String getDescLabel() {
        return descLabel;
    }

    /**
     * @param descLabel the descLabel to set
     */
    public void setDescLabel(String descLabel) {
        this.descLabel = descLabel;
    }

    /**
     * @return the observedProperty
     */
    public String getObservedProperty() {
        return observedProperty;
    }

    /**
     * @param observedProperty the observedProperty to set
     */
    public void setObservedProperty(String observedProperty) {
        this.observedProperty = observedProperty;
    }

    /**
     * @return the featureOfInterest
     */
    public String getFeatureOfInterest() {
        return featureOfInterest;
    }

    /**
     * @param featureOfInterest the featureOfInterest to set
     */
    public void setFeatureOfInterest(String featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }    
}
