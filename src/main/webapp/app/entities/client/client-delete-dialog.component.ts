import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClient } from 'app/shared/model/client.model';
import { ClientService } from './client.service';

@Component({
  templateUrl: './client-delete-dialog.component.html',
})
export class ClientDeleteDialogComponent {
  client?: IClient;

  constructor(protected clientService: ClientService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.clientService.delete(id).subscribe(() => {
      this.eventManager.broadcast('clientListModification');
      this.activeModal.close();
    });
  }
}
