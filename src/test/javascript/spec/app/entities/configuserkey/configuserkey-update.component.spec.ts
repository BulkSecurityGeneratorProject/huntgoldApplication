/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfiguserkeyUpdateComponent } from 'app/entities/configuserkey/configuserkey-update.component';
import { ConfiguserkeyService } from 'app/entities/configuserkey/configuserkey.service';
import { Configuserkey } from 'app/shared/model/configuserkey.model';

describe('Component Tests', () => {
    describe('Configuserkey Management Update Component', () => {
        let comp: ConfiguserkeyUpdateComponent;
        let fixture: ComponentFixture<ConfiguserkeyUpdateComponent>;
        let service: ConfiguserkeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfiguserkeyUpdateComponent]
            })
                .overrideTemplate(ConfiguserkeyUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfiguserkeyUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguserkeyService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Configuserkey(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configuserkey = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Configuserkey();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configuserkey = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
