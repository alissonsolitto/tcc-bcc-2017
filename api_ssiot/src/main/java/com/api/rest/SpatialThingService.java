package com.api.rest;

import com.api.model.PropertyModel;
import com.api.model.SensorDeviceModel;
import com.api.model.SensorModel;
import com.api.model.SpatialThingModel;

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
import com.framework.ssn.skeleton.PropertySSN;
import com.framework.ssn.skeleton.SensingDeviceSSN;
import com.framework.ssn.skeleton.SensorSSN;
import com.framework.ssn.skeleton.SpatialThingSSN;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/spatialthing")
public class SpatialThingService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProperty(SpatialThingModel spaceModel) {

        OntModel model = LoadOntologyFuseki.Load();

        SpatialThingSSN space = new SpatialThingSSN(model);
        space.AddPoint(spaceModel.getIndName(),
                spaceModel.getDescLabel(),
                spaceModel.getDescComment(),
                spaceModel.getLat(),
                spaceModel.getLon(),
                spaceModel.getAlt());

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Instância inserida com sucesso - " + spaceModel.getIndName();
        return Response.status(201).entity(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListType> getProperty() {
        
        OntModel model = LoadOntologyFuseki.Load();
        
        SpatialThingSSN space = new SpatialThingSSN(model);
        ArrayList<clsListType> lstResult = space.ListPoint();
        
        return lstResult;
    }
    
    @DELETE
    @Path("/{indName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delProperty(@PathParam("indName") String indName) {
        
        OntModel model = LoadOntologyFuseki.Load();

        SpatialThingSSN space = new SpatialThingSSN(model);
        space.DeletePoint(indName);        

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Instância excluida com sucesso - " + indName;
        return Response.status(201).entity(result).build();
        
    }

}
