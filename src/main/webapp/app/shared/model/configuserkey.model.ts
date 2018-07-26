import { Moment } from 'moment';

export interface IConfiguserkey {
    id?: number;
    platform?: string;
    apikey?: string;
    secretkey?: string;
    createby?: string;
    createdate?: Moment;
    updateby?: string;
    updatedate?: Moment;
    remarks?: string;
    delflag?: number;
}

export class Configuserkey implements IConfiguserkey {
    constructor(
        public id?: number,
        public platform?: string,
        public apikey?: string,
        public secretkey?: string,
        public createby?: string,
        public createdate?: Moment,
        public updateby?: string,
        public updatedate?: Moment,
        public remarks?: string,
        public delflag?: number
    ) {}
}
