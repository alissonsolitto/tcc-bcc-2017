import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';

import { FeatureInterestService } from '../../service/feature-interest.service'

declare const $: any;

@Component({
  selector: 'app-feature-interest',
  templateUrl: './feature-interest.component.html',
  styleUrls: ['./feature-interest.component.css'],
  providers: [FeatureInterestService]
})
export class FeatureInterestComponent implements OnInit {
  lstFeature: any[] = [];
  indName: string;
  label: string;
  comment: string;

  constructor(private featureService: FeatureInterestService) { }

  ngOnInit() {
    this.getAllFeature();
  }

  getAllFeature(){
    this.featureService.getAll().subscribe(x => {
      this.lstFeature = x;
    });
  }

  delete(property: any) {
    this.featureService.delete(property.nome).subscribe(x => {
      alert(x.text());

      //Atualiza a lista local apenas
      this.lstFeature = this.lstFeature.filter(item => item !== property);
    });
  }

  create() {
    let propFeature = {
      indName: this.indName,
      label: this.label,
      comment: this.comment
    }

    this.featureService.create(propFeature).subscribe(x => {
      alert(x.text());
      this.getAllFeature();
      $('#formAdd').modal('hide');
      this.clearField();
    });
  }

  cancel() {
    $('#formAdd').modal('hide');
    this.clearField();
  }

  clearField(){
    this.indName = '';
    this.label = '';
    this.comment = '';
  }
}

