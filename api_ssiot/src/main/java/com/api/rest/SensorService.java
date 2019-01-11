package com.api.rest;

import com.api.model.SensorModel;
import com.framework.model.clsListSensorType;
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
import com.framework.ssn.URI;
import com.framework.ssn.skeleton.SensorSSN;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/sensor")
public class SensorService {

    //Sensores de todos os tipos
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListSensorType> getSensorAll() {

        OntModel model = LoadOntologyFuseki.Load();
        SensorSSN sensorSSN = new SensorSSN(model);
        ArrayList<clsListSensorType> lstResult = new ArrayList<clsListSensorType>();

        model.setStrictMode(false);
        OntClass clsTypeSensingDevice = model.getOntClass(URI.ssn + "SensingDevice");

        for (Iterator i = clsTypeSensingDevice.listSubClasses(); i.hasNext();) {

            OntClass a = (OntClass) i.next();

            for (clsListSensorType s : sensorSSN.ListAll(a.getLocalName())) {
                lstResult.add(s);
            }
        }

        return lstResult;
    }

    @GET
    @Path("/{typeName}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListType> getSensor(@PathParam("typeName") String typeName) {

        OntModel model = LoadOntologyFuseki.Load();

        SensorSSN sensorSSN = new SensorSSN(model);
        ArrayList<clsListType> lstResult = sensorSSN.List(typeName);

        return lstResult;
    }

    //LISTA TODOS OS SENSORES DO TIPO "SENSOR"
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<clsListType> getSensor() {

        OntModel model = LoadOntologyFuseki.Load();

        SensorSSN sensorSSN = new SensorSSN(model);
        ArrayList<clsListType> lstResult = sensorSSN.List("Sensor");

        return lstResult;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(SensorModel sensor) {

        OntModel model = LoadOntologyFuseki.Load();

        SensorSSN sensorSSN = new SensorSSN(model);

        sensorSSN.AddSensor(
                sensor.getIdSensor(),
                sensor.getTypeName(),
                sensor.getCooment(),
                sensor.getLabel());

        for (String p : sensor.getObserves()) {
            sensorSSN.AddObserves(sensor.getIdSensor(), p);
        }

        for (String m : sensor.getHasMeasurementCapability()) {
            sensorSSN.AddHasMeasurementCapability(sensor.getIdSensor(), m);
        }

        sensorSSN.AddHasLocation(
                sensor.getIdSensor(),
                sensor.getHasLocation());

        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);

        String result = "Sensor inserido com sucesso - " + sensor.getIdSensor();
        return Response.status(201).entity(result).build();
    }
}
