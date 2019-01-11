import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

@Injectable()
export class FeatureInterestService {
  constructor(private http: Http) { }

  getAll() {
    return this.http.get('/api/featureofinterest').map((response: Response) => response.json());
  }

  create(feature: any) {
    return this.http.post('/api/featureofinterest', feature);
  }

  delete(indName: string) {
    return this.http.delete('/api/featureofinterest/' + indName);
  }
}
