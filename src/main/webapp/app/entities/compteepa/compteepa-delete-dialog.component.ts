import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompteepa } from 'app/shared/model/compteepa.model';
import { CompteepaService } from './compteepa.service';

@Component({
  templateUrl: './compteepa-delete-dialog.component.html',
})
export class CompteepaDeleteDialogComponent {
  compteepa?: ICompteepa;

  constructor(protected compteepaService: CompteepaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.compteepaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('compteepaListModification');
      this.activeModal.close();
    });
  }
}
