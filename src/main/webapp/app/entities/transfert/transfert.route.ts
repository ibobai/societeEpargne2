import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITransfert, Transfert } from 'app/shared/model/transfert.model';
import { TransfertService } from './transfert.service';
import { TransfertComponent } from './transfert.component';
import { TransfertDetailComponent } from './transfert-detail.component';
import { TransfertUpdateComponent } from './transfert-update.component';

@Injectable({ providedIn: 'root' })
export class TransfertResolve implements Resolve<ITransfert> {
  constructor(private service: TransfertService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITransfert> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((transfert: HttpResponse<Transfert>) => {
          if (transfert.body) {
            return of(transfert.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Transfert());
  }
}

export const transfertRoute: Routes = [
  {
    path: '',
    component: TransfertComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Transferts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TransfertDetailComponent,
    resolve: {
      transfert: TransfertResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Transferts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TransfertUpdateComponent,
    resolve: {
      transfert: TransfertResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Transferts',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TransfertUpdateComponent,
    resolve: {
      transfert: TransfertResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Transferts',
    },
    canActivate: [UserRouteAccessService],
  },
];
