export class SensorSimulator {

  constructor() {
    this.uri = "";
    this.nome = "";
    this.label = "";
    this.comment = "";
    this.property = "";
    this.objObservation = {
      nameSensor: "",
      descComment: "",
      descLabel: "",
      observedProperty: "",
      featureOfInterest: "",
      value: 0,
      symbol: "",
      unit: ""
    };
    this.objObservationString = "";
    this.apiSimulator = 0;
  }

  uri: string;
  nome: string;
  label: string;
  comment: string;
  type: string;
  property: string;
  objObservation: {
    nameSensor: string;
    descComment: string;
    descLabel: string;
    observedProperty: string;
    featureOfInterest: string;
    value: number;
    symbol: string;
    unit: string;
  };
  objObservationString: string;
  apiSimulator: number; // 0 - Stop; 1 - Start
}