package com.api.rest;

import com.api.model.FeatureofInterestModel;
import com.api.model.PropertyModel;
import com.api.model.SensorDeviceModel;
import com.api.model.SensorModel;

import com.framework.model.clsListType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.framework.ssn.LoadOntology;
import com.framework.ssn.LoadOntologyFuseki;
import com.framework.ssn.skeleton.FeatureOfInterestSSN;
import com.framework.ssn.skeleton.PropertySSN;
import com.framework.ssn.skeleton.SensingDeviceSSN;
import com.framework.ssn.skeleton.SensorSSN;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/featureofinterest")
public class FeatureofInteresetService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProperty(FeatureofInterestModel featureModel) {

        OntModel model = LoadOntologyFuseki.Load();

        FeatureOfInterestSSN feat = new FeatureOfInterestSSN(model);
        feat.AddFeatureOfInterestInd(featureModel.getIndName(),
                featureModel.getComment(),
                featureModel.getLabel());

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Instância inserida com sucesso - " + featureModel.getIndName();
        return Response.status(201).entity(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListType> getProperty() {
        
        OntModel model = LoadOntologyFuseki.Load();
        
        FeatureOfInterestSSN feat = new FeatureOfInterestSSN(model);
        ArrayList<clsListType> lstResult = feat.ListInd();
        
        return lstResult;
    }
    
    @DELETE
    @Path("/{indName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delProperty(@PathParam("indName") String indName) {
        
        OntModel model = LoadOntologyFuseki.Load();

        FeatureOfInterestSSN feat = new FeatureOfInterestSSN(model);
        feat.DeleteInd(indName);        

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Instância excluida com sucesso - " + indName;
        return Response.status(201).entity(result).build();
        
    }

}
