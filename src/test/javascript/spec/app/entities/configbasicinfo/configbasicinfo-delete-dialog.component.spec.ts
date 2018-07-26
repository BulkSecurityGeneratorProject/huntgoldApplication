/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfigbasicinfoDeleteDialogComponent } from 'app/entities/configbasicinfo/configbasicinfo-delete-dialog.component';
import { ConfigbasicinfoService } from 'app/entities/configbasicinfo/configbasicinfo.service';

describe('Component Tests', () => {
    describe('Configbasicinfo Management Delete Component', () => {
        let comp: ConfigbasicinfoDeleteDialogComponent;
        let fixture: ComponentFixture<ConfigbasicinfoDeleteDialogComponent>;
        let service: ConfigbasicinfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfigbasicinfoDeleteDialogComponent]
            })
                .overrideTemplate(ConfigbasicinfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfigbasicinfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigbasicinfoService);
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
