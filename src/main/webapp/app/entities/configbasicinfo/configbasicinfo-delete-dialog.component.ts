import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';
import { ConfigbasicinfoService } from './configbasicinfo.service';

@Component({
    selector: 'jhi-configbasicinfo-delete-dialog',
    templateUrl: './configbasicinfo-delete-dialog.component.html'
})
export class ConfigbasicinfoDeleteDialogComponent {
    configbasicinfo: IConfigbasicinfo;

    constructor(
        private configbasicinfoService: ConfigbasicinfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configbasicinfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'configbasicinfoListModification',
                content: 'Deleted an configbasicinfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configbasicinfo-delete-popup',
    template: ''
})
export class ConfigbasicinfoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configbasicinfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConfigbasicinfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.configbasicinfo = configbasicinfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
