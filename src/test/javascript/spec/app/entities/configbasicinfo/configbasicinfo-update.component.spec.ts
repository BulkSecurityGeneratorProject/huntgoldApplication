/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfigbasicinfoUpdateComponent } from 'app/entities/configbasicinfo/configbasicinfo-update.component';
import { ConfigbasicinfoService } from 'app/entities/configbasicinfo/configbasicinfo.service';
import { Configbasicinfo } from 'app/shared/model/configbasicinfo.model';

describe('Component Tests', () => {
    describe('Configbasicinfo Management Update Component', () => {
        let comp: ConfigbasicinfoUpdateComponent;
        let fixture: ComponentFixture<ConfigbasicinfoUpdateComponent>;
        let service: ConfigbasicinfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfigbasicinfoUpdateComponent]
            })
                .overrideTemplate(ConfigbasicinfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfigbasicinfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigbasicinfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Configbasicinfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configbasicinfo = entity;
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
                    const entity = new Configbasicinfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configbasicinfo = entity;
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
