import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompteepa } from 'app/shared/model/compteepa.model';

type EntityResponseType = HttpResponse<ICompteepa>;
type EntityArrayResponseType = HttpResponse<ICompteepa[]>;

@Injectable({ providedIn: 'root' })
export class CompteepaService {
  public resourceUrl = SERVER_API_URL + 'api/compteepas';

  constructor(protected http: HttpClient) {}

  create(compteepa: ICompteepa): Observable<EntityResponseType> {
    return this.http.post<ICompteepa>(this.resourceUrl, compteepa, { observe: 'response' });
  }

  update(compteepa: ICompteepa): Observable<EntityResponseType> {
    return this.http.put<ICompteepa>(this.resourceUrl, compteepa, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompteepa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompteepa[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
