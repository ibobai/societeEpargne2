import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComptecou } from 'app/shared/model/comptecou.model';
import { ComptecouService } from './comptecou.service';

@Component({
  templateUrl: './comptecou-delete-dialog.component.html',
})
export class ComptecouDeleteDialogComponent {
  comptecou?: IComptecou;

  constructor(protected comptecouService: ComptecouService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.comptecouService.delete(id).subscribe(() => {
      this.eventManager.broadcast('comptecouListModification');
      this.activeModal.close();
    });
  }
}
