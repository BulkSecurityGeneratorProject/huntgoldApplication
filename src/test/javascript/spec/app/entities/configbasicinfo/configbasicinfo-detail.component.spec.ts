/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfigbasicinfoDetailComponent } from 'app/entities/configbasicinfo/configbasicinfo-detail.component';
import { Configbasicinfo } from 'app/shared/model/configbasicinfo.model';

describe('Component Tests', () => {
    describe('Configbasicinfo Management Detail Component', () => {
        let comp: ConfigbasicinfoDetailComponent;
        let fixture: ComponentFixture<ConfigbasicinfoDetailComponent>;
        const route = ({ data: of({ configbasicinfo: new Configbasicinfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfigbasicinfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConfigbasicinfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfigbasicinfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.configbasicinfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
