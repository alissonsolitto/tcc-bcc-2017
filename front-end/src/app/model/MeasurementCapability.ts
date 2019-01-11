export class MeasurementCapability {
  indName: string;
  indNameProperty: string;
  typeNameProperty: string;
  forPropertyMeasurementCapability: string;
  min: {
    value: number,
    symbol: string,
    unit: string
  };
  max: {
    value: number,
    symbol: string,
    unit: string
  }
}