package com.api.rest;

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
import com.framework.ssn.skeleton.SensingDeviceSSN;
import com.framework.ssn.skeleton.SensorSSN;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/sensordevice")
public class SensorDeviceService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensorDevice(SensorDeviceModel sensorDevice) {

        OntModel model = LoadOntologyFuseki.Load();

        SensingDeviceSSN senDevice = new SensingDeviceSSN(model);
        senDevice.AddType(sensorDevice.getTypeName(),
                sensorDevice.getLabel(),
                sensorDevice.getComment());

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Modelo inserido com sucesso - " + sensorDevice.getTypeName();
        return Response.status(201).entity(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListType> getSensorDevice() {
        
        OntModel model = LoadOntologyFuseki.Load();
        
        SensingDeviceSSN senDevice = new SensingDeviceSSN(model);
        ArrayList<clsListType> lstResult = senDevice.List();
        
        return lstResult;
    }
    
    @DELETE
    @Path("/{className}")
    public Response delSensorDevice(@PathParam("className") String className) {
        
        OntModel model = LoadOntologyFuseki.Load();

        SensingDeviceSSN senDevice = new SensingDeviceSSN(model);
        senDevice.Delete(className);
        
        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Modelo excluido com sucesso - " + className;
        return Response.status(201).entity(result).build();
        
    }

}
