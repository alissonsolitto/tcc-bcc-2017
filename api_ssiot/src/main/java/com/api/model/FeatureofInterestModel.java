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
public class FeatureofInterestModel {
    
    private String indName;
    private String label;
    private String comment;

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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
