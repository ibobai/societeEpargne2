import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConseiller } from 'app/shared/model/conseiller.model';
import { ConseillerService } from './conseiller.service';

@Component({
  templateUrl: './conseiller-delete-dialog.component.html',
})
export class ConseillerDeleteDialogComponent {
  conseiller?: IConseiller;

  constructor(
    protected conseillerService: ConseillerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.conseillerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('conseillerListModification');
      this.activeModal.close();
    });
  }
}
