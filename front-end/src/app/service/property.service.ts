import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class PropertyService {
  constructor(private http: Http) { }

  getAll() {
    return this.http.get('/api/property').map((response: Response) => response.json());
  }

  create(property: any) {
    return this.http.post('/api/property', property);
  }

  delete(indName: string) {
    return this.http.delete('/api/property/' + indName);
  }
}
