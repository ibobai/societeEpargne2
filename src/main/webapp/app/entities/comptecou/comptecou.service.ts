import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IComptecou } from 'app/shared/model/comptecou.model';

type EntityResponseType = HttpResponse<IComptecou>;
type EntityArrayResponseType = HttpResponse<IComptecou[]>;

@Injectable({ providedIn: 'root' })
export class ComptecouService {
  public resourceUrl = SERVER_API_URL + 'api/comptecous';

  constructor(protected http: HttpClient) {}

  create(comptecou: IComptecou): Observable<EntityResponseType> {
    return this.http.post<IComptecou>(this.resourceUrl, comptecou, { observe: 'response' });
  }

  update(comptecou: IComptecou): Observable<EntityResponseType> {
    return this.http.put<IComptecou>(this.resourceUrl, comptecou, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IComptecou>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IComptecou[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
