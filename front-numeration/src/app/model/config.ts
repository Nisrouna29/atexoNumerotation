export interface Config {
  type: string;
  criterionType: string;
  orderIndex: number;
  prefix: string;
  suffix: string;
}

export interface FirstNameConfig extends Config {
  length: number;
}

export interface NameConfig extends Config {
  length: number;
}

export interface BirthdateConfig extends Config {
  dateFormat: string;
}

export interface CounterConfig extends Config {
  length: number;
}
