import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.BriefGroupeJHipsterClientModule),
      },
      {
        path: 'comptecou',
        loadChildren: () => import('./comptecou/comptecou.module').then(m => m.BriefGroupeJHipsterComptecouModule),
      },
      {
        path: 'compteepa',
        loadChildren: () => import('./compteepa/compteepa.module').then(m => m.BriefGroupeJHipsterCompteepaModule),
      },
      {
        path: 'compte',
        loadChildren: () => import('./compte/compte.module').then(m => m.BriefGroupeJHipsterCompteModule),
      },
      {
        path: 'conseiller',
        loadChildren: () => import('./conseiller/conseiller.module').then(m => m.BriefGroupeJHipsterConseillerModule),
      },
      {
        path: 'transfert',
        loadChildren: () => import('./transfert/transfert.module').then(m => m.BriefGroupeJHipsterTransfertModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BriefGroupeJHipsterEntityModule {}
