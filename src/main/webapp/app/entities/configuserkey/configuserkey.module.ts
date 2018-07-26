import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HuntgoldApplicationSharedModule } from 'app/shared';
import {
    ConfiguserkeyComponent,
    ConfiguserkeyDetailComponent,
    ConfiguserkeyUpdateComponent,
    ConfiguserkeyDeletePopupComponent,
    ConfiguserkeyDeleteDialogComponent,
    configuserkeyRoute,
    configuserkeyPopupRoute
} from './';

const ENTITY_STATES = [...configuserkeyRoute, ...configuserkeyPopupRoute];

@NgModule({
    imports: [HuntgoldApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConfiguserkeyComponent,
        ConfiguserkeyDetailComponent,
        ConfiguserkeyUpdateComponent,
        ConfiguserkeyDeleteDialogComponent,
        ConfiguserkeyDeletePopupComponent
    ],
    entryComponents: [
        ConfiguserkeyComponent,
        ConfiguserkeyUpdateComponent,
        ConfiguserkeyDeleteDialogComponent,
        ConfiguserkeyDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HuntgoldApplicationConfiguserkeyModule {}
