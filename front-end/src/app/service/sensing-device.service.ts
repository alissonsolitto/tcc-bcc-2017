import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class SensingDeviceService {
  constructor(private http: Http) { }

  getAll() {
    return this.http.get('/api/sensordevice').map((response: Response) => response.json());
  }

  create(sensorDevice: any) {
    return this.http.post('/api/sensordevice', sensorDevice);
  }

  delete(className: string) {
    return this.http.delete('/api/sensordevice/' + className);
  }
}