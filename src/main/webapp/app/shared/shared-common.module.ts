import { NgModule } from '@angular/core';

import { HuntgoldApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [HuntgoldApplicationSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [HuntgoldApplicationSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class HuntgoldApplicationSharedCommonModule {}
