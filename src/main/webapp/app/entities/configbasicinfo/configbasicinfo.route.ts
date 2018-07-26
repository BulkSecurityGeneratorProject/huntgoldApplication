import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Configbasicinfo } from 'app/shared/model/configbasicinfo.model';
import { ConfigbasicinfoService } from './configbasicinfo.service';
import { ConfigbasicinfoComponent } from './configbasicinfo.component';
import { ConfigbasicinfoDetailComponent } from './configbasicinfo-detail.component';
import { ConfigbasicinfoUpdateComponent } from './configbasicinfo-update.component';
import { ConfigbasicinfoDeletePopupComponent } from './configbasicinfo-delete-dialog.component';
import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';

@Injectable({ providedIn: 'root' })
export class ConfigbasicinfoResolve implements Resolve<IConfigbasicinfo> {
    constructor(private service: ConfigbasicinfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((configbasicinfo: HttpResponse<Configbasicinfo>) => configbasicinfo.body));
        }
        return of(new Configbasicinfo());
    }
}

export const configbasicinfoRoute: Routes = [
    {
        path: 'configbasicinfo',
        component: ConfigbasicinfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configbasicinfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configbasicinfo/:id/view',
        component: ConfigbasicinfoDetailComponent,
        resolve: {
            configbasicinfo: ConfigbasicinfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configbasicinfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configbasicinfo/new',
        component: ConfigbasicinfoUpdateComponent,
        resolve: {
            configbasicinfo: ConfigbasicinfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configbasicinfos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configbasicinfo/:id/edit',
        component: ConfigbasicinfoUpdateComponent,
        resolve: {
            configbasicinfo: ConfigbasicinfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configbasicinfos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configbasicinfoPopupRoute: Routes = [
    {
        path: 'configbasicinfo/:id/delete',
        component: ConfigbasicinfoDeletePopupComponent,
        resolve: {
            configbasicinfo: ConfigbasicinfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configbasicinfos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
