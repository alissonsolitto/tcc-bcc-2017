import { Component, OnInit } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';

declare const $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(public location: Location) { }

  ngOnInit() {
    $.material.options.autofill = true;
    $.material.init();
  }

  isNotVisible(path) {
    var titlee = this.location.prepareExternalUrl(this.location.path());
    titlee = titlee.slice(1);
    if (path == titlee) {
      return false;
    }
    else {
      return true;
    }
  }

  isNotVisibleSideBar(path) {
    var titlee = this.location.prepareExternalUrl(this.location.path());
    titlee = titlee.slice(1);
    if (path == titlee) {
      $(".main-panel").css({'width':'100%'});
      return false;

    }
    else {
      $(".main-panel").css({'width':'calc(100% - 260px)'});
      return true;
    }
  }

  
}
