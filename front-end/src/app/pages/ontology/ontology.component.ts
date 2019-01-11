import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ontology',
  templateUrl: './ontology.component.html',
  styleUrls: ['./ontology.component.css']
})
export class OntologyComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    //faz um get na ontologia do fuseki e apresenta
    //usa a visualização em grafo da ontologia
  }

}
