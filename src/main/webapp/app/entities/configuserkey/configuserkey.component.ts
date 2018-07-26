import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConfiguserkey } from 'app/shared/model/configuserkey.model';
import { Principal } from 'app/core';
import { ConfiguserkeyService } from './configuserkey.service';

@Component({
    selector: 'jhi-configuserkey',
    templateUrl: './configuserkey.component.html'
})
export class ConfiguserkeyComponent implements OnInit, OnDestroy {
    configuserkeys: IConfiguserkey[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private configuserkeyService: ConfiguserkeyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.configuserkeyService.query().subscribe(
            (res: HttpResponse<IConfiguserkey[]>) => {
                this.configuserkeys = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInConfiguserkeys();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConfiguserkey) {
        return item.id;
    }

    registerChangeInConfiguserkeys() {
        this.eventSubscriber = this.eventManager.subscribe('configuserkeyListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
