/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HuntgoldApplicationTestModule } from '../../../test.module';
import { ConfiguserkeyDetailComponent } from 'app/entities/configuserkey/configuserkey-detail.component';
import { Configuserkey } from 'app/shared/model/configuserkey.model';

describe('Component Tests', () => {
    describe('Configuserkey Management Detail Component', () => {
        let comp: ConfiguserkeyDetailComponent;
        let fixture: ComponentFixture<ConfiguserkeyDetailComponent>;
        const route = ({ data: of({ configuserkey: new Configuserkey(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HuntgoldApplicationTestModule],
                declarations: [ConfiguserkeyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConfiguserkeyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfiguserkeyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.configuserkey).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
