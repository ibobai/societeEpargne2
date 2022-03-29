import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConseiller } from 'app/shared/model/conseiller.model';

type EntityResponseType = HttpResponse<IConseiller>;
type EntityArrayResponseType = HttpResponse<IConseiller[]>;

@Injectable({ providedIn: 'root' })
export class ConseillerService {
  public resourceUrl = SERVER_API_URL + 'api/conseillers';

  constructor(protected http: HttpClient) {}

  create(conseiller: IConseiller): Observable<EntityResponseType> {
    return this.http.post<IConseiller>(this.resourceUrl, conseiller, { observe: 'response' });
  }

  update(conseiller: IConseiller): Observable<EntityResponseType> {
    return this.http.put<IConseiller>(this.resourceUrl, conseiller, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConseiller>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConseiller[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
