import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './authentication/auth.guard';

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SensingDeviceComponent } from './pages/sensing-device/sensing-device.component';
import { PropertyComponent } from './pages/property/property.component';
import { FeatureInterestComponent } from './pages/feature-interest/feature-interest.component';
import { SensorComponent } from './pages/sensor/sensor.component';
import { SimulatorComponent } from './pages/simulator/simulator.component';
import { FusekiComponent } from './pages/fuseki/fuseki.component';
import { OntologyComponent } from './pages/ontology/ontology.component';

const routes: Routes =[
    //Exemplo rota com autenticação
    //{ path: 'dashboard',      component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'sensing-device', component: SensingDeviceComponent },
    { path: 'property', component: PropertyComponent },
    { path: 'feature-of-interest', component: FeatureInterestComponent },
    { path: 'sensor', component: SensorComponent },
    { path: 'simulator', component: SimulatorComponent },
    { path: 'fuseki', component: FusekiComponent },    
    { path: 'ontology', component: OntologyComponent },
    { path: '',          redirectTo: 'dashboard', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
