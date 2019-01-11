package com.api.rest;

import com.api.model.MeasurementCapabilityModel;
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
import com.framework.ssn.skeleton.MeasurementCapabilitySSN;
import com.framework.ssn.skeleton.PropertySSN;
import com.framework.ssn.skeleton.SensingDeviceSSN;
import com.framework.ssn.skeleton.SensorSSN;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;

@Path("/measurementcapability")
public class MeasurementCapabilityService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProperty(MeasurementCapabilityModel measureModel) {

        OntModel model = LoadOntologyFuseki.Load();
        
        MeasurementCapabilitySSN mc = new MeasurementCapabilitySSN(model);
        
        //Min
        mc.AddMeasurementMinMax(
                measureModel.getIndNameProperty(), 
                "Min", 
                measureModel.getMin().getValue(), 
                measureModel.getMin().getSymbol(), 
                measureModel.getMin().getUnit());
 
        //Max
        mc.AddMeasurementMinMax(
                measureModel.getIndNameProperty(), 
                "Max", 
                measureModel.getMax().getValue(), 
                measureModel.getMax().getSymbol(), 
                measureModel.getMax().getUnit());
        
        //Adicionando MIN e MAX na Propriedade
        mc.AddMeasurementProperty(
                measureModel.getIndNameProperty(),
                measureModel.getTypeNameProperty());
        
        //Criando pai caso seja necessário
        if(!measureModel.getIndName().isEmpty()){
            mc.AddMeasurementCapability(
                measureModel.getIndName()
            );
        }
        
        //Adicionando a nova propriedade ao pai
        mc.AddHasMeasurementProperty(measureModel.getIndName(), measureModel.getIndNameProperty()); 
        
        //Adicionando forProperty
        mc.AddForPropertyMeasurementCapability(measureModel.getIndName(), measureModel.getForPropertyMeasurementCapability());
        
        //Salvar no fuseki
        LoadOntologyFuseki.setModel(model);
        
        String result = "MeasurementCapability inserido com sucesso";
        return Response.status(201).entity(result).build();
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<clsListType> getProperty() {
//        
//        OntModel model = LoadOntologyFuseki.Load();
//        
////        PropertySSN prop = new PropertySSN(model);
////        ArrayList<clsListType> lstResult = prop.ListInd();
//        
//        return lstResult;
//    }
//    
//    @DELETE
//    @Path("/{indName}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response delProperty(@PathParam("indName") String indName) {
//        
//        OntModel model = LoadOntologyFuseki.Load();
//
////        PropertySSN prop = new PropertySSN(model);
////        prop.DeleteInd(indName);        
//
//        //Salvar no fuseki
//        LoadOntologyFuseki.setModel(model);
//        
//        String result = "Instância excluida com sucesso - " + indName;
//        return Response.status(201).entity(result).build();
//        
//    }

}
