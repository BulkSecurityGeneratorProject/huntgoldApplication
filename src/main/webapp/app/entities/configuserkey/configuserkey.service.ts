import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConfiguserkey } from 'app/shared/model/configuserkey.model';

type EntityResponseType = HttpResponse<IConfiguserkey>;
type EntityArrayResponseType = HttpResponse<IConfiguserkey[]>;

@Injectable({ providedIn: 'root' })
export class ConfiguserkeyService {
    private resourceUrl = SERVER_API_URL + 'api/configuserkeys';

    constructor(private http: HttpClient) {}

    create(configuserkey: IConfiguserkey): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configuserkey);
        return this.http
            .post<IConfiguserkey>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(configuserkey: IConfiguserkey): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configuserkey);
        return this.http
            .put<IConfiguserkey>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IConfiguserkey>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IConfiguserkey[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(configuserkey: IConfiguserkey): IConfiguserkey {
        const copy: IConfiguserkey = Object.assign({}, configuserkey, {
            createdate: configuserkey.createdate != null && configuserkey.createdate.isValid() ? configuserkey.createdate.toJSON() : null,
            updatedate: configuserkey.updatedate != null && configuserkey.updatedate.isValid() ? configuserkey.updatedate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdate = res.body.createdate != null ? moment(res.body.createdate) : null;
        res.body.updatedate = res.body.updatedate != null ? moment(res.body.updatedate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((configuserkey: IConfiguserkey) => {
            configuserkey.createdate = configuserkey.createdate != null ? moment(configuserkey.createdate) : null;
            configuserkey.updatedate = configuserkey.updatedate != null ? moment(configuserkey.updatedate) : null;
        });
        return res;
    }
}
