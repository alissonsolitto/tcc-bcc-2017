import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';

import { SensingDeviceService } from '../../service/sensing-device.service'
import { PropertyService } from '../../service/property.service'
import { MeasurementService } from '../../service/measurement.service'
import { SpatialThingService } from '../../service/spatial-thing.service'
import { SensorService } from '../../service/sensor.service'

import { MeasurementCapability } from '../../model/MeasurementCapability'
import { SpatialThing } from '../../model/SpatialThing'

declare const $: any;

@Component({
  selector: 'app-sensor',
  templateUrl: './sensor.component.html',
  styleUrls: ['./sensor.component.css'],
  providers: [
    SensingDeviceService,
    PropertyService,
    MeasurementService,
    SpatialThingService,
    SensorService
  ]
})
export class SensorComponent implements OnInit {

  constructor(
    private sensingDevice: SensingDeviceService,
    private propService: PropertyService,
    private measurementService: MeasurementService,
    private spatialService: SpatialThingService,
    private sensorService: SensorService
  ) { }

  //Sensor
  indName: string;
  label: string;
  comment: string;

  //Sensing Device
  lstSensingDevice: any[] = [];
  selectSensingDevice: any = {
    label: "Select sensor types"
  };

  //Property
  lstProperty: any[] = [];
  lstPropertySelect: any[] = [];
  propertySelect: any;

  //Measurement
  lstMeasurementProperty: any[] = ["Frequency", "Accuracy", "Latency", "Precision", "Resolution", "Sensitivity", "Selectivity", "Drift"];
  selectMeasurementProperty: string = "Select Property";

  lstMeasurement: MeasurementCapability[] = [];
  lstMeasurementAux: MeasurementCapability[] = [];
  measurementAdd: MeasurementCapability;

  //SpatialThing
  spatialThingAdd: SpatialThing;


  ngOnInit() {
    this.getAllSesingDevice();
    this.getAllProperty();
    this.cleanMeasurement();
    this.cleanSpatialThing();
  }

  getAllSesingDevice() {
    this.sensingDevice.getAll().subscribe(x => {
      this.lstSensingDevice = x;
    });
  }

  getAllProperty() {
    this.propService.getAll().subscribe(x => {
      this.lstProperty = x;
    });
  }

  onClickSensingDevice(sensing: any) {
    this.selectSensingDevice = sensing;
  }

  delProperty(prop: any) {
    this.lstPropertySelect = this.lstPropertySelect.filter(item => item !== prop);
  }

  delMeasurement(m: any) {
    this.lstMeasurement = this.lstMeasurement.filter(item => item !== m);
    this.lstMeasurementAux = this.lstMeasurement.filter(item => item.forPropertyMeasurementCapability == this.propertySelect.nome);
  }

  onClickAddProperty(prop: any) {
    this.lstPropertySelect.push(prop);
  }

  onSelectProperty(prop: any) {
    this.propertySelect = prop;
    this.lstMeasurementAux = this.lstMeasurement.filter(item => item.forPropertyMeasurementCapability == prop.nome);
  }

  cleanMeasurement() {
    this.selectMeasurementProperty = "Select Property";

    this.measurementAdd = {
      indName: "",
      indNameProperty: "",
      typeNameProperty: "",
      forPropertyMeasurementCapability: "",
      min: {
        value: 0,
        symbol: "",
        unit: ""
      },
      max: {
        value: 0,
        symbol: "",
        unit: ""
      }
    };
  }

  cleanSpatialThing() {
    this.spatialThingAdd = {
      indName: "",
      descLabel: "",
      descComment: "",
      lat: 0,
      lon: 0,
      alt: 0
    };
  }

  onClickMeasurementList(mp: string) {
    this.selectMeasurementProperty = mp;
  }

  createMeasurement() {
    this.measurementAdd.indName = "Measurement" + this.indName + this.propertySelect.nome;
    this.measurementAdd.indNameProperty = this.measurementAdd.indName + this.measurementAdd.indNameProperty;
    this.measurementAdd.typeNameProperty = this.selectMeasurementProperty;
    this.measurementAdd.forPropertyMeasurementCapability = this.propertySelect.nome;
    this.lstMeasurement.push(this.measurementAdd);

    //Filtrando itens para lista
    this.lstMeasurementAux = this.lstMeasurement.filter(item => item.forPropertyMeasurementCapability == this.propertySelect.nome);

    $('#addMeasurement').modal('hide');
    this.cleanMeasurement();
  }

  cancelMeasurement() {
    $('#addMeasurement').modal('hide');
    this.cleanMeasurement();
  }

  cancelSensor() {

  }

  createSensor() {

    //Add Measurement
    for (let m of this.lstMeasurement) {
      setInterval(() => {
        this.measurementService.create(m).subscribe(x => {
          console.log(x.text());
        });
      }, 1000);
    }

    //Add Spatial Thing
    this.spatialService.create(this.spatialThingAdd).subscribe(x => {
      console.log(x.text());
    });

    //Add Sensor
    let hasMeasurementCapability: string[] = [];
    let observes: string[] = [];

    this.lstPropertySelect.forEach(element => {
      hasMeasurementCapability.push("Measurement" + this.indName + element.nome);
      observes.push(element.nome);
    });

    let sensor = {
      idSensor: this.indName,
      typeName: this.selectSensingDevice.nome,
      label: this.label,
      cooment: this.comment,
      hasMeasurementCapability: hasMeasurementCapability,
      observes: observes,
      hasLocation: this.spatialThingAdd.indName
    }

    this.sensorService.create(sensor).subscribe(x => {
      alert(x.text());
    });
  }
}
