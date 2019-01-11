import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class MeasurementService {
  constructor(private http: Http) { }

  getAll() {
    return this.http.get('/api/measurementcapability').map((response: Response) => response.json());
  }

  create(obj: any) {
    return this.http.post('/api/measurementcapability', obj);
  }

  delete(indName: string) {
    return this.http.delete('/api/measurementcapability/' + indName);
  }
}
