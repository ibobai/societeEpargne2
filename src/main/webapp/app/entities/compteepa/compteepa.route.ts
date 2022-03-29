import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompteepa, Compteepa } from 'app/shared/model/compteepa.model';
import { CompteepaService } from './compteepa.service';
import { CompteepaComponent } from './compteepa.component';
import { CompteepaDetailComponent } from './compteepa-detail.component';
import { CompteepaUpdateComponent } from './compteepa-update.component';

@Injectable({ providedIn: 'root' })
export class CompteepaResolve implements Resolve<ICompteepa> {
  constructor(private service: CompteepaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompteepa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((compteepa: HttpResponse<Compteepa>) => {
          if (compteepa.body) {
            return of(compteepa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Compteepa());
  }
}

export const compteepaRoute: Routes = [
  {
    path: '',
    component: CompteepaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Compteepas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CompteepaDetailComponent,
    resolve: {
      compteepa: CompteepaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Compteepas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CompteepaUpdateComponent,
    resolve: {
      compteepa: CompteepaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Compteepas',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CompteepaUpdateComponent,
    resolve: {
      compteepa: CompteepaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Compteepas',
    },
    canActivate: [UserRouteAccessService],
  },
];
