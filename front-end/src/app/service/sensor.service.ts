import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class SensorService {
  constructor(private http: Http) { }

  getSensorAll() {
    return this.http.get('/api/sensor/all').map((response: Response) => response.json());
  }

  getAll() {
    return this.http.get('/api/sensor').map((response: Response) => response.json());
  }

  create(sensorDevice: any) {
    return this.http.post('/api/sensor', sensorDevice);
  }

  delete(className: string) {
    return this.http.delete('/api/sensor/' + className);
  }
}
