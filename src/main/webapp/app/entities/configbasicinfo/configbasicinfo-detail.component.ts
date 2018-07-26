import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';

@Component({
    selector: 'jhi-configbasicinfo-detail',
    templateUrl: './configbasicinfo-detail.component.html'
})
export class ConfigbasicinfoDetailComponent implements OnInit {
    configbasicinfo: IConfigbasicinfo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configbasicinfo }) => {
            this.configbasicinfo = configbasicinfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
