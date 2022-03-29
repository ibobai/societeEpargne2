import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransfert } from 'app/shared/model/transfert.model';

type EntityResponseType = HttpResponse<ITransfert>;
type EntityArrayResponseType = HttpResponse<ITransfert[]>;

@Injectable({ providedIn: 'root' })
export class TransfertService {
  public resourceUrl = SERVER_API_URL + 'api/transferts';

  constructor(protected http: HttpClient) {}

  create(transfert: ITransfert): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transfert);
    return this.http
      .post<ITransfert>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(transfert: ITransfert): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(transfert);
    return this.http
      .put<ITransfert>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITransfert>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITransfert[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(transfert: ITransfert): ITransfert {
    const copy: ITransfert = Object.assign({}, transfert, {
      date: transfert.date && transfert.date.isValid() ? transfert.date.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((transfert: ITransfert) => {
        transfert.date = transfert.date ? moment(transfert.date) : undefined;
      });
    }
    return res;
  }
}
