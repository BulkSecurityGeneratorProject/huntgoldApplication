import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Configuserkey } from 'app/shared/model/configuserkey.model';
import { ConfiguserkeyService } from './configuserkey.service';
import { ConfiguserkeyComponent } from './configuserkey.component';
import { ConfiguserkeyDetailComponent } from './configuserkey-detail.component';
import { ConfiguserkeyUpdateComponent } from './configuserkey-update.component';
import { ConfiguserkeyDeletePopupComponent } from './configuserkey-delete-dialog.component';
import { IConfiguserkey } from 'app/shared/model/configuserkey.model';

@Injectable({ providedIn: 'root' })
export class ConfiguserkeyResolve implements Resolve<IConfiguserkey> {
    constructor(private service: ConfiguserkeyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((configuserkey: HttpResponse<Configuserkey>) => configuserkey.body));
        }
        return of(new Configuserkey());
    }
}

export const configuserkeyRoute: Routes = [
    {
        path: 'configuserkey',
        component: ConfiguserkeyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuserkeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuserkey/:id/view',
        component: ConfiguserkeyDetailComponent,
        resolve: {
            configuserkey: ConfiguserkeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuserkeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuserkey/new',
        component: ConfiguserkeyUpdateComponent,
        resolve: {
            configuserkey: ConfiguserkeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuserkeys'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuserkey/:id/edit',
        component: ConfiguserkeyUpdateComponent,
        resolve: {
            configuserkey: ConfiguserkeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuserkeys'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configuserkeyPopupRoute: Routes = [
    {
        path: 'configuserkey/:id/delete',
        component: ConfiguserkeyDeletePopupComponent,
        resolve: {
            configuserkey: ConfiguserkeyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Configuserkeys'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
