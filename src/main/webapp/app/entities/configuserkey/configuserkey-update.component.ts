import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IConfiguserkey } from 'app/shared/model/configuserkey.model';
import { ConfiguserkeyService } from './configuserkey.service';

@Component({
    selector: 'jhi-configuserkey-update',
    templateUrl: './configuserkey-update.component.html'
})
export class ConfiguserkeyUpdateComponent implements OnInit {
    private _configuserkey: IConfiguserkey;
    isSaving: boolean;
    createdate: string;
    updatedate: string;

    constructor(private configuserkeyService: ConfiguserkeyService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ configuserkey }) => {
            this.configuserkey = configuserkey;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.configuserkey.createdate = moment(this.createdate, DATE_TIME_FORMAT);
        this.configuserkey.updatedate = moment(this.updatedate, DATE_TIME_FORMAT);
        if (this.configuserkey.id !== undefined) {
            this.subscribeToSaveResponse(this.configuserkeyService.update(this.configuserkey));
        } else {
            this.subscribeToSaveResponse(this.configuserkeyService.create(this.configuserkey));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConfiguserkey>>) {
        result.subscribe((res: HttpResponse<IConfiguserkey>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get configuserkey() {
        return this._configuserkey;
    }

    set configuserkey(configuserkey: IConfiguserkey) {
        this._configuserkey = configuserkey;
        this.createdate = moment(configuserkey.createdate).format(DATE_TIME_FORMAT);
        this.updatedate = moment(configuserkey.updatedate).format(DATE_TIME_FORMAT);
    }
}
