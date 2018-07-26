import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HuntgoldApplicationConfiguserkeyModule } from './configuserkey/configuserkey.module';
import { HuntgoldApplicationConfigbasicinfoModule } from './configbasicinfo/configbasicinfo.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HuntgoldApplicationConfiguserkeyModule,
        HuntgoldApplicationConfigbasicinfoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HuntgoldApplicationEntityModule {}
