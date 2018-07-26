/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfigbasicinfoComponent } from 'app/entities/configbasicinfo/configbasicinfo.component';
import { ConfigbasicinfoService } from 'app/entities/configbasicinfo/configbasicinfo.service';
import { Configbasicinfo } from 'app/shared/model/configbasicinfo.model';

describe('Component Tests', () => {
    describe('Configbasicinfo Management Component', () => {
        let comp: ConfigbasicinfoComponent;
        let fixture: ComponentFixture<ConfigbasicinfoComponent>;
        let service: ConfigbasicinfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfigbasicinfoComponent],
                providers: []
            })
                .overrideTemplate(ConfigbasicinfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfigbasicinfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigbasicinfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Configbasicinfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.configbasicinfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
