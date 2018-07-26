import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HuntgoldApplicationSharedModule } from 'app/shared';
import {
    ConfigbasicinfoComponent,
    ConfigbasicinfoDetailComponent,
    ConfigbasicinfoUpdateComponent,
    ConfigbasicinfoDeletePopupComponent,
    ConfigbasicinfoDeleteDialogComponent,
    configbasicinfoRoute,
    configbasicinfoPopupRoute
} from './';

const ENTITY_STATES = [...configbasicinfoRoute, ...configbasicinfoPopupRoute];

@NgModule({
    imports: [HuntgoldApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConfigbasicinfoComponent,
        ConfigbasicinfoDetailComponent,
        ConfigbasicinfoUpdateComponent,
        ConfigbasicinfoDeleteDialogComponent,
        ConfigbasicinfoDeletePopupComponent
    ],
    entryComponents: [
        ConfigbasicinfoComponent,
        ConfigbasicinfoUpdateComponent,
        ConfigbasicinfoDeleteDialogComponent,
        ConfigbasicinfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HuntgoldApplicationConfigbasicinfoModule {}
