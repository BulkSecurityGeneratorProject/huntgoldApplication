import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConfigbasicinfo } from 'app/shared/model/configbasicinfo.model';

type EntityResponseType = HttpResponse<IConfigbasicinfo>;
type EntityArrayResponseType = HttpResponse<IConfigbasicinfo[]>;

@Injectable({ providedIn: 'root' })
export class ConfigbasicinfoService {
    private resourceUrl = SERVER_API_URL + 'api/configbasicinfos';

    constructor(private http: HttpClient) {}

    create(configbasicinfo: IConfigbasicinfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configbasicinfo);
        return this.http
            .post<IConfigbasicinfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(configbasicinfo: IConfigbasicinfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configbasicinfo);
        return this.http
            .put<IConfigbasicinfo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IConfigbasicinfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IConfigbasicinfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(configbasicinfo: IConfigbasicinfo): IConfigbasicinfo {
        const copy: IConfigbasicinfo = Object.assign({}, configbasicinfo, {
            createdate:
                configbasicinfo.createdate != null && configbasicinfo.createdate.isValid() ? configbasicinfo.createdate.toJSON() : null,
            updatedate:
                configbasicinfo.updatedate != null && configbasicinfo.updatedate.isValid() ? configbasicinfo.updatedate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdate = res.body.createdate != null ? moment(res.body.createdate) : null;
        res.body.updatedate = res.body.updatedate != null ? moment(res.body.updatedate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((configbasicinfo: IConfigbasicinfo) => {
            configbasicinfo.createdate = configbasicinfo.createdate != null ? moment(configbasicinfo.createdate) : null;
            configbasicinfo.updatedate = configbasicinfo.updatedate != null ? moment(configbasicinfo.updatedate) : null;
        });
        return res;
    }
}
