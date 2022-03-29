import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICompte } from 'app/shared/model/compte.model';

type EntityResponseType = HttpResponse<ICompte>;
type EntityArrayResponseType = HttpResponse<ICompte[]>;

@Injectable({ providedIn: 'root' })
export class CompteService {
  public resourceUrl = SERVER_API_URL + 'api/comptes';

  constructor(protected http: HttpClient) {}

  create(compte: ICompte): Observable<EntityResponseType> {
    return this.http.post<ICompte>(this.resourceUrl, compte, { observe: 'response' });
  }

  update(compte: ICompte): Observable<EntityResponseType> {
    return this.http.put<ICompte>(this.resourceUrl, compte, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICompte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICompte[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
