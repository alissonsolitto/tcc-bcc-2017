import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';

import { SensingDeviceService } from '../../service/sensing-device.service'

declare const $: any;

@Component({
  selector: 'app-sensing-device',
  templateUrl: './sensing-device.component.html',
  styleUrls: ['./sensing-device.component.css'],
  providers: [SensingDeviceService]
})
export class SensingDeviceComponent implements OnInit {
  lstSensorDevice: any[] = [];
  typeName: string;
  label: string;
  comment: string;

  constructor(
    private sensingDevice: SensingDeviceService
  ) { }

  ngOnInit() {
    this.getAllSesingDevice();
  }

  getAllSesingDevice() {
    this.sensingDevice.getAll().subscribe(x => {
      this.lstSensorDevice = x;
    });
  }

  delete(sensor: any) {
    this.sensingDevice.delete(sensor.nome).subscribe(x => {
      alert(x.text());

      //Atualiza a lista local apenas
      this.lstSensorDevice = this.lstSensorDevice.filter(item => item !== sensor);
    });
  }

  create() {
    let sensorDevice = {
      typeName: this.typeName,
      label: this.label,
      comment: this.comment
    }

    this.sensingDevice.create(sensorDevice).subscribe(x => {
      alert(x.text());
      this.getAllSesingDevice();
      $('#addSensingDevice').modal('hide');
      this.clearField();
    });
  }

  cancel() {
    $('#addSensingDevice').modal('hide');
    this.clearField();
  }

  clearField() {
    this.typeName = '';
    this.label = '';
    this.comment = '';
  }
}
