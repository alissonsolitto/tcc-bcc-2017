/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.model;

/**
 *
 * @author Alisson Solitto
 */
public class clsProperty {
    //URI Completa
    private String nameProperty;
    private String valueProperty;
    
    public clsProperty(String name, String value){
        this.nameProperty = name;
        this.valueProperty = value;
    }

    /**
     * @return the nameProperty
     */
    public String getNameProperty() {
        return nameProperty;
    }

    /**
     * @param nameProperty the nameProperty to set
     */
    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    /**
     * @return the valueProperty
     */
    public String getValueProperty() {
        return valueProperty;
    }

    /**
     * @param valueProperty the valueProperty to set
     */
    public void setValueProperty(String valueProperty) {
        this.valueProperty = valueProperty;
    }    
}
