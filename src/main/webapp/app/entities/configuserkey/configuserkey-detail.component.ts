import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConfiguserkey } from 'app/shared/model/configuserkey.model';

@Component({
    selector: 'jhi-configuserkey-detail',
    templateUrl: './configuserkey-detail.component.html'
})
export class ConfiguserkeyDetailComponent implements OnInit {
    configuserkey: IConfiguserkey;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configuserkey }) => {
            this.configuserkey = configuserkey;
        });
    }

    previousState() {
        window.history.back();
    }
}
