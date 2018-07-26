import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';
import { ConfigbasicinfoService } from './configbasicinfo.service';

@Component({
    selector: 'jhi-configbasicinfo-update',
    templateUrl: './configbasicinfo-update.component.html'
})
export class ConfigbasicinfoUpdateComponent implements OnInit {
    private _configbasicinfo: IConfigbasicinfo;
    isSaving: boolean;
    createdate: string;
    updatedate: string;

    constructor(private configbasicinfoService: ConfigbasicinfoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ configbasicinfo }) => {
            this.configbasicinfo = configbasicinfo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.configbasicinfo.createdate = moment(this.createdate, DATE_TIME_FORMAT);
        this.configbasicinfo.updatedate = moment(this.updatedate, DATE_TIME_FORMAT);
        if (this.configbasicinfo.id !== undefined) {
            this.subscribeToSaveResponse(this.configbasicinfoService.update(this.configbasicinfo));
        } else {
            this.subscribeToSaveResponse(this.configbasicinfoService.create(this.configbasicinfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConfigbasicinfo>>) {
        result.subscribe((res: HttpResponse<IConfigbasicinfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get configbasicinfo() {
        return this._configbasicinfo;
    }

    set configbasicinfo(configbasicinfo: IConfigbasicinfo) {
        this._configbasicinfo = configbasicinfo;
        this.createdate = moment(configbasicinfo.createdate).format(DATE_TIME_FORMAT);
        this.updatedate = moment(configbasicinfo.updatedate).format(DATE_TIME_FORMAT);
    }
}
