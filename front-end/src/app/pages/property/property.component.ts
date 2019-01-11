import { Component, OnInit } from '@angular/core';
import { NgModel, NgForm } from '@angular/forms';

import { PropertyService } from '../../service/property.service'

declare const $: any;

@Component({
  selector: 'app-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css'],
  providers: [PropertyService]
})
export class PropertyComponent implements OnInit {
  lstProperty: any[] = [];
  indName: string;
  label: string;
  comment: string;

  constructor(private propService: PropertyService) { }

  ngOnInit() {
    this.getAllProperty();
  }

  getAllProperty(){
    this.propService.getAll().subscribe(x => {
      this.lstProperty = x;
    });
  }

  delete(property: any) {
    this.propService.delete(property.nome).subscribe(x => {
      alert(x.text());

      //Atualiza a lista local apenas
      this.lstProperty = this.lstProperty.filter(item => item !== property);
    });
  }

  create() {
    let propObj = {
      indName: this.indName,
      label: this.label,
      comment: this.comment
    }

    this.propService.create(propObj).subscribe(x => {
      alert(x.text());
      this.getAllProperty();
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
