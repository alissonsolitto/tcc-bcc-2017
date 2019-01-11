import { Component, OnInit } from '@angular/core';
import { ISlimScrollOptions } from 'ngx-slimscroll';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    //{ path: 'dashboard', title: 'Dashboard', icon: 'dvr', class: '' },
    { path: 'sensing-device', title: 'Sensing Device', icon: 'add', class: '' },
    { path: 'property', title: 'Property', icon: 'add', class: '' },
    { path: 'feature-of-interest', title: 'Feature of Interest', icon: 'add', class: '' },
    { path: 'sensor', title: 'Sensor', icon: 'add', class: '' },
    { path: 'simulator', title: 'Simulator', icon: 'speaker_phone', class: '' },    
    { path: 'ontology', title: 'Ontology', icon: 'blur_on', class: '' },
    { path: 'fuseki', title: 'Fuseki', icon: 'settings_system_daydream', class: '' }
];

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
    menuItems: any[];
    opts: ISlimScrollOptions;

    constructor() { }

    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);

        this.opts = {
            position: "right",
            barBackground: "#D9D9D9",
            barOpacity: "0.8",
            barWidth: "6",
            barBorderRadius: "20",
            barMargin: "0",
            gridBackground: "#D9D9D9",
            gridOpacity: "1",
            gridWidth: "2",
            gridBorderRadius: "20",
            gridMargin: "0",
            alwaysVisible: true,
            visibleTimeout: 1000
        };
    }
    isMobileMenu() {
        if ($(window).width() > 991) {
            return false;
        }
        return true;
    };
}