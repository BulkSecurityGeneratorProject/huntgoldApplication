/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfiguserkeyDeleteDialogComponent } from 'app/entities/configuserkey/configuserkey-delete-dialog.component';
import { ConfiguserkeyService } from 'app/entities/configuserkey/configuserkey.service';

describe('Component Tests', () => {
    describe('Configuserkey Management Delete Component', () => {
        let comp: ConfiguserkeyDeleteDialogComponent;
        let fixture: ComponentFixture<ConfiguserkeyDeleteDialogComponent>;
        let service: ConfiguserkeyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfiguserkeyDeleteDialogComponent]
            })
                .overrideTemplate(ConfiguserkeyDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfiguserkeyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguserkeyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
