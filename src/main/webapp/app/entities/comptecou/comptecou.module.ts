import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BriefGroupeJHipsterSharedModule } from 'app/shared/shared.module';
import { ComptecouComponent } from './comptecou.component';
import { ComptecouDetailComponent } from './comptecou-detail.component';
import { ComptecouUpdateComponent } from './comptecou-update.component';
import { ComptecouDeleteDialogComponent } from './comptecou-delete-dialog.component';
import { comptecouRoute } from './comptecou.route';

@NgModule({
  imports: [BriefGroupeJHipsterSharedModule, RouterModule.forChild(comptecouRoute)],
  declarations: [ComptecouComponent, ComptecouDetailComponent, ComptecouUpdateComponent, ComptecouDeleteDialogComponent],
  entryComponents: [ComptecouDeleteDialogComponent],
})
export class BriefGroupeJHipsterComptecouModule {}
