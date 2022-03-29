import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BriefGroupeJHipsterSharedModule } from 'app/shared/shared.module';
import { ConseillerComponent } from './conseiller.component';
import { ConseillerDetailComponent } from './conseiller-detail.component';
import { ConseillerUpdateComponent } from './conseiller-update.component';
import { ConseillerDeleteDialogComponent } from './conseiller-delete-dialog.component';
import { conseillerRoute } from './conseiller.route';

@NgModule({
  imports: [BriefGroupeJHipsterSharedModule, RouterModule.forChild(conseillerRoute)],
  declarations: [ConseillerComponent, ConseillerDetailComponent, ConseillerUpdateComponent, ConseillerDeleteDialogComponent],
  entryComponents: [ConseillerDeleteDialogComponent],
})
export class BriefGroupeJHipsterConseillerModule {}
