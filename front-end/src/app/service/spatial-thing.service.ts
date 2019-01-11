import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class SpatialThingService {
  constructor(private http: Http) { }

  getAll() {
    return this.http.get('/api/spatialthing').map((response: Response) => response.json());
  }

  create(obj: any) {
    return this.http.post('/api/spatialthing', obj);
  }

  delete(indName: string) {
    return this.http.delete('/api/spatialthing/' + indName);
  }
}
