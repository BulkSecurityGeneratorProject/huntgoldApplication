import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfiguserkey } from 'app/shared/model/configuserkey.model';
import { ConfiguserkeyService } from './configuserkey.service';

@Component({
    selector: 'jhi-configuserkey-delete-dialog',
    templateUrl: './configuserkey-delete-dialog.component.html'
})
export class ConfiguserkeyDeleteDialogComponent {
    configuserkey: IConfiguserkey;

    constructor(
        private configuserkeyService: ConfiguserkeyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configuserkeyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'configuserkeyListModification',
                content: 'Deleted an configuserkey'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configuserkey-delete-popup',
    template: ''
})
export class ConfiguserkeyDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configuserkey }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConfiguserkeyDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.configuserkey = configuserkey;
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
