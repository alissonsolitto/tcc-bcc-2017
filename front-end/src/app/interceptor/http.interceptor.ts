import { Injectable } from "@angular/core";
import { ConnectionBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers } from "@angular/http";
import { Observable } from "rxjs/Rx";

import { environment } from '../../environments/environment';

@Injectable()
export class InterceptedHttp extends Http {

  public pendingRequests: number = 0;
  public showLoading: boolean = false;

  constructor(backend: ConnectionBackend, 
              defaultOptions: RequestOptions) {
    super(backend, defaultOptions);
  }

  get(url: string, options?: RequestOptionsArgs): Observable<Response> {
    url = this.updateUrl(url);    
    return this.intercept(super.get(url, this.getRequestOptionArgs(options)));
  }

  post(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    url = this.updateUrl(url);  
    return this.intercept(super.post(url, body, this.getRequestOptionArgs(options)));
  }

  put(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    url = this.updateUrl(url);
    return this.intercept(super.put(url, body, this.getRequestOptionArgs(options)));
  }

  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
    url = this.updateUrl(url);
    return this.intercept(super.delete(url, this.getRequestOptionArgs(options)));
  }

  private intercept(observable: Observable<Response>): Observable<Response> {
    return observable
      //Erro na requisição
      .catch(this.handleError)
      //Sucesso
      .do((res: Response) => {
        this.turnOnModal();
      }, (err: any) => {        
        this.turnOffModal();
      })
      //Finalizando
      .finally(() => {
        var timer = Observable.timer(1000);
        timer.subscribe(t => {
          this.turnOffModal();
        });
      });
  }

  private handleError(error: Response) {
    return Observable.throw(error.json().error || 'Ocorreu um erro no servidor');
  }

  private updateUrl(req: string) {
    return environment.urlAPi + req;
  }

  private getRequestOptionArgs(options?: RequestOptionsArgs): RequestOptionsArgs {

    let currentUser = JSON.parse(localStorage.getItem('currentUser'));

    if (options == null) {
      options = new RequestOptions();
    }
    if (options.headers == null) {
      options.headers = new Headers();
    }

    options.headers.append('Content-Type', 'application/json; charset=UTF-8');

    if (currentUser && currentUser.token) {
      options.headers.append('Authorization', currentUser.token);
    }
    
    return options;
  }

  private turnOnModal() {
    this.pendingRequests++;
    console.log("carregando....");
    // Mostra loading
    //this.spinnerService.show('spinner-global','Carregando');
  }

  private turnOffModal() {
    if ((--this.pendingRequests) === 0) {
      // Esconde loading
      console.log("esconde loading....");      
      //this.spinnerService.hideAll();
    }
  }

}