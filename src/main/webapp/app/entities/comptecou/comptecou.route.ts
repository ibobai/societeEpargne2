import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComptecou, Comptecou } from 'app/shared/model/comptecou.model';
import { ComptecouService } from './comptecou.service';
import { ComptecouComponent } from './comptecou.component';
import { ComptecouDetailComponent } from './comptecou-detail.component';
import { ComptecouUpdateComponent } from './comptecou-update.component';

@Injectable({ providedIn: 'root' })
export class ComptecouResolve implements Resolve<IComptecou> {
  constructor(private service: ComptecouService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComptecou> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comptecou: HttpResponse<Comptecou>) => {
          if (comptecou.body) {
            return of(comptecou.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Comptecou());
  }
}

export const comptecouRoute: Routes = [
  {
    path: '',
    component: ComptecouComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Comptecous',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ComptecouDetailComponent,
    resolve: {
      comptecou: ComptecouResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comptecous',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ComptecouUpdateComponent,
    resolve: {
      comptecou: ComptecouResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comptecous',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ComptecouUpdateComponent,
    resolve: {
      comptecou: ComptecouResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comptecous',
    },
    canActivate: [UserRouteAccessService],
  },
];
