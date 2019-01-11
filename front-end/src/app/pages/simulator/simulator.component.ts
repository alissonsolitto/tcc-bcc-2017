import { Component, OnInit } from '@angular/core';

import { SensorService } from '../../service/sensor.service'
import { FeatureInterestService } from '../../service/feature-interest.service'
import { ObservationService } from '../../service/observation.service'

import { SensorSimulator } from '../../model/SensorSimulator'

import * as Chartist from 'chartist';

@Component({
  selector: 'app-simulator',
  templateUrl: './simulator.component.html',
  styleUrls: ['./simulator.component.css'],
  providers: [
    SensorService,
    FeatureInterestService,
    ObservationService
  ]
})
export class SimulatorComponent implements OnInit {

  lstFeature: any[] = [];
  selectFeature: any = {
    label: "Select feature"
  };

  lstSensor: any[] = [];
  lstSensorSimulator: any[] = [];
  sensorSelected: SensorSimulator;

  lstConsoleSimulator: string[] = [];

  dataDailySalesChart = {
    labels: [],
    series: [[]]
  };

  constructor(
    private sensorService: SensorService,
    private featureService: FeatureInterestService,
    private observationService: ObservationService
  ) { }

  ngOnInit() {
    this.sensorSelected = new SensorSimulator();
    this.getAllSensor();
    this.getAllFeature();
    this.simulatorSensor();
  }

  getAllSensor() {
    this.sensorService.getSensorAll().subscribe(x => {
      this.lstSensor = x;
    });
  }

  getAllFeature() {
    this.featureService.getAll().subscribe(x => {
      this.lstFeature = x;
    });
  }

  onClickFeature(f: any) {
    this.selectFeature = f;
    this.sensorSelected.objObservation.featureOfInterest = f.nome;
    this.sensorSelected.objObservationString = JSON.stringify(this.sensorSelected.objObservation);
  }

  onClickAddSensor(sen: any) {

    let obs = {
      nameSensor: sen.nome,
      descComment: "",
      descLabel: "",
      observedProperty: sen.property,
      featureOfInterest: "",
      value: 0,
      symbol: "",
      unit: ""
    };

    sen.objObservation = obs;
    this.lstSensorSimulator.push(sen);
  }

  startSimulator(sen: any) {
    sen.apiSimulator = 1;
  }

  stopSimulator(sen: any) {
    sen.apiSimulator = 0;
  }

  randomNumber(minimum, maximum) {
    return Math.round(Math.random() * (maximum - minimum) + minimum);
  }

  simulatorSensor() {
    setInterval(() => {

      this.lstSensorSimulator.forEach(element => {

        if (element.apiSimulator > 0) {

          element.objObservation.value = parseInt(element.objObservation.value) + this.randomNumber(3, -3);

          this.observationService.create(element.objObservation).subscribe(x => {
            this.lstConsoleSimulator.push(x.text() + ".............. Value: " + element.objObservation.value);

            this.dataDailySalesChart.labels.push('');
            this.dataDailySalesChart.series[0].push(element.objObservation.value);

            this.initChart("#dailySalesChart_" + element.nome);
          });
        }

      });

    }, 2000);
  }

  sensorSettings(sen: any) {
    this.sensorSelected = sen;
    this.sensorSelected.objObservationString = JSON.stringify(this.sensorSelected.objObservation);
  }

  onModelChange(event) {
    this.sensorSelected.objObservationString = JSON.stringify(this.sensorSelected.objObservation);
  }

  initChart(idChart: string) {
    
    const optionsDailySalesChart: any = {
      lineSmooth: Chartist.Interpolation.cardinal({
        tension: 0
      }),
      low: 0,
      high: 50, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
      chartPadding: { top: 0, right: 0, bottom: 0, left: 0 },
    }

    var dailySalesChart = new Chartist.Line(idChart, this.dataDailySalesChart, optionsDailySalesChart);
  }
}
