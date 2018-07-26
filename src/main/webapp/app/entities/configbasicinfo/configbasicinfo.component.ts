import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';
import { Principal } from 'app/core';
import { ConfigbasicinfoService } from './configbasicinfo.service';

@Component({
    selector: 'jhi-configbasicinfo',
    templateUrl: './configbasicinfo.component.html'
})
export class ConfigbasicinfoComponent implements OnInit, OnDestroy {
    configbasicinfos: IConfigbasicinfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private configbasicinfoService: ConfigbasicinfoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.configbasicinfoService.query().subscribe(
            (res: HttpResponse<IConfigbasicinfo[]>) => {
                this.configbasicinfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInConfigbasicinfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConfigbasicinfo) {
        return item.id;
    }

    registerChangeInConfigbasicinfos() {
        this.eventSubscriber = this.eventManager.subscribe('configbasicinfoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
