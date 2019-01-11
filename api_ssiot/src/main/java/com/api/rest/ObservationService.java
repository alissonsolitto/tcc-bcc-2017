package com.api.rest;

import com.api.model.FeatureofInterestModel;
import com.api.model.MeasurementCapabilityModel;
import com.api.model.ObservationModel;
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
import com.framework.ssn.Uteis;
import com.framework.ssn.skeleton.FeatureOfInterestSSN;
import com.framework.ssn.skeleton.MeasurementCapabilitySSN;
import com.framework.ssn.skeleton.ObservationSSN;
import com.framework.ssn.skeleton.PropertySSN;
import com.framework.ssn.skeleton.SensingDeviceSSN;
import com.framework.ssn.skeleton.SensorOutput;
import com.framework.ssn.skeleton.SensorSSN;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/observation")
public class ObservationService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProperty(ObservationModel obsModel) {

        OntModel model = LoadOntologyFuseki.Load();
        String nameObs = "Obs_" + obsModel.getNameSensor() + "_" + Uteis.getDateTimeUnderline();
        String nameOut = "Output_" + obsModel.getNameSensor() + "_" + Uteis.getDateTimeUnderline();

        //Incluindo SensorOutput
        SensorOutput sensorOut = new SensorOutput(model);
        sensorOut.AddSensorOutput(nameOut);
        sensorOut.AddValuesSensorOutput(
                nameOut, 
                obsModel.getValue(), 
                obsModel.getSymbol(), 
                obsModel.getUnit());
        
        //Incluindo Observation
        ObservationSSN obsSSN = new ObservationSSN(model);
        obsSSN.AddObservation(
                nameObs, 
                obsModel.getDescComment(), 
                obsModel.getDescLabel());
        
        //Propriedades
        obsSSN.AddObservedBy(nameObs, obsModel.getNameSensor());
        obsSSN.AddObservedProperty(nameObs, obsModel.getObservedProperty());
        obsSSN.AddFeatureOfInterest(nameObs, obsModel.getFeatureOfInterest());
        obsSSN.AddObservationResult(nameObs, nameOut);
        obsSSN.AddObservationResultTime(nameObs);
        

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "Instância inserida com sucesso - " + nameObs;
        return Response.status(201).entity(result).build();
    }
}
