import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BriefGroupeJHipsterSharedModule } from 'app/shared/shared.module';
import { CompteepaComponent } from './compteepa.component';
import { CompteepaDetailComponent } from './compteepa-detail.component';
import { CompteepaUpdateComponent } from './compteepa-update.component';
import { CompteepaDeleteDialogComponent } from './compteepa-delete-dialog.component';
import { compteepaRoute } from './compteepa.route';

@NgModule({
  imports: [BriefGroupeJHipsterSharedModule, RouterModule.forChild(compteepaRoute)],
  declarations: [CompteepaComponent, CompteepaDetailComponent, CompteepaUpdateComponent, CompteepaDeleteDialogComponent],
  entryComponents: [CompteepaDeleteDialogComponent],
})
export class BriefGroupeJHipsterCompteepaModule {}
