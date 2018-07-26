import { Moment } from 'moment';

export interface IConfigbasicinfo {
    id?: number;
    configcode?: string;
    configname?: string;
    configvalue?: string;
    parentid?: string;
    configtypename?: string;
    configtype?: string;
    createby?: string;
    createdate?: Moment;
    updateby?: string;
    updatedate?: Moment;
    remarks?: string;
    delflag?: number;
}

export class Configbasicinfo implements IConfigbasicinfo {
    constructor(
        public id?: number,
        public configcode?: string,
        public configname?: string,
        public configvalue?: string,
        public parentid?: string,
        public configtypename?: string,
        public configtype?: string,
        public createby?: string,
        public createdate?: Moment,
        public updateby?: string,
        public updatedate?: Moment,
        public remarks?: string,
        public delflag?: number
    ) {}
}
