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
public class SpatialThingModel {
    
    private String indName;
    private String descLabel;
    private String descComment;
    private float lat;
    private float lon;
    private float alt;    

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
     * @return the lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(float lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public float getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

    /**
     * @return the alt
     */
    public float getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(float alt) {
        this.alt = alt;
    }
}
