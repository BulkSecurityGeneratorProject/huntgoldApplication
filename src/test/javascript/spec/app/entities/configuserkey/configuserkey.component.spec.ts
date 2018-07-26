/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfiguserkeyComponent } from 'app/entities/configuserkey/configuserkey.component';
import { ConfiguserkeyService } from 'app/entities/configuserkey/configuserkey.service';
import { Configuserkey } from 'app/shared/model/configuserkey.model';

describe('Component Tests', () => {
    describe('Configuserkey Management Component', () => {
        let comp: ConfiguserkeyComponent;
        let fixture: ComponentFixture<ConfiguserkeyComponent>;
        let service: ConfiguserkeyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfiguserkeyComponent],
                providers: []
            })
                .overrideTemplate(ConfiguserkeyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfiguserkeyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguserkeyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Configuserkey(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.configuserkeys[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
