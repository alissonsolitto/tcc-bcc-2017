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
public class MeasurementCapabilityModel {
    
    public static class MeasurementPropertyModel{
        private int value;
        private String symbol;
        private String unit;

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
    
    private String indName;
    private String indNameProperty; //Precisao
    private String typeNameProperty; //Acurracy
    private String forPropertyMeasurementCapability; //Nome da Property
    private MeasurementPropertyModel min;
    private MeasurementPropertyModel max;
    
    /**
     * @return the indName
     */
    public String getIndName() {
        return indName;
    }

    /**
     * @param indName the indName to set
     */
    public void setIndName(String indName) {
        this.indName = indName;
    }

    /**
     * @return the indNameProperty
     */
    public String getIndNameProperty() {
        return indNameProperty;
    }

    /**
     * @param indNameProperty the indNameProperty to set
     */
    public void setIndNameProperty(String indNameProperty) {
        this.indNameProperty = indNameProperty;
    }

    /**
     * @return the typeNameProperty
     */
    public String getTypeNameProperty() {
        return typeNameProperty;
    }

    /**
     * @param typeNameProperty the typeNameProperty to set
     */
    public void setTypeNameProperty(String typeNameProperty) {
        this.typeNameProperty = typeNameProperty;
    }

    /**
     * @return the min
     */
    public MeasurementPropertyModel getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(MeasurementPropertyModel min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public MeasurementPropertyModel getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(MeasurementPropertyModel max) {
        this.max = max;
    }

    /**
     * @return the forPropertyMeasurementCapability
     */
    public String getForPropertyMeasurementCapability() {
        return forPropertyMeasurementCapability;
    }

    /**
     * @param forPropertyMeasurementCapability the forPropertyMeasurementCapability to set
     */
    public void setForPropertyMeasurementCapability(String forPropertyMeasurementCapability) {
        this.forPropertyMeasurementCapability = forPropertyMeasurementCapability;
    }
}
