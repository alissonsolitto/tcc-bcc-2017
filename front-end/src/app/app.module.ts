import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http, XHRBackend, RequestOptions } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { AuthGuard } from './authentication/auth.guard';
import { AuthenticationService } from './authentication/authentication.service';
import { httpFactory } from "./interceptor/http.factory";

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SensingDeviceComponent } from './pages/sensing-device/sensing-device.component';
import { PropertyComponent } from './pages/property/property.component';
import { FeatureInterestComponent } from './pages/feature-interest/feature-interest.component';
import { SensorComponent } from './pages/sensor/sensor.component';
import { FusekiComponent } from './pages/fuseki/fuseki.component';
import { SimulatorComponent } from './pages/simulator/simulator.component';
import { OntologyComponent } from './pages/ontology/ontology.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SensingDeviceComponent,
    PropertyComponent,
    FeatureInterestComponent,
    SensorComponent,
    FusekiComponent,
    SimulatorComponent,
    OntologyComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,    
    {
        provide: Http,
        useFactory: httpFactory,
        deps: [XHRBackend, RequestOptions]
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
