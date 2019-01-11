import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class ObservationService {
  constructor(private http: Http) { }

  create(obs: any) {
    return this.http.post('/api/observation', obs);
  }
}
