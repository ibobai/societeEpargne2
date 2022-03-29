import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BriefGroupeJHipsterSharedModule } from 'app/shared/shared.module';
import { TransfertComponent } from './transfert.component';
import { TransfertDetailComponent } from './transfert-detail.component';
import { TransfertUpdateComponent } from './transfert-update.component';
import { TransfertDeleteDialogComponent } from './transfert-delete-dialog.component';
import { transfertRoute } from './transfert.route';

@NgModule({
  imports: [BriefGroupeJHipsterSharedModule, RouterModule.forChild(transfertRoute)],
  declarations: [TransfertComponent, TransfertDetailComponent, TransfertUpdateComponent, TransfertDeleteDialogComponent],
  entryComponents: [TransfertDeleteDialogComponent],
})
export class BriefGroupeJHipsterTransfertModule {}
