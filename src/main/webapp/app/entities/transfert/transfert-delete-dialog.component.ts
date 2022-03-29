import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITransfert } from 'app/shared/model/transfert.model';
import { TransfertService } from './transfert.service';

@Component({
  templateUrl: './transfert-delete-dialog.component.html',
})
export class TransfertDeleteDialogComponent {
  transfert?: ITransfert;

  constructor(protected transfertService: TransfertService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transfertService.delete(id).subscribe(() => {
      this.eventManager.broadcast('transfertListModification');
      this.activeModal.close();
    });
  }
}
