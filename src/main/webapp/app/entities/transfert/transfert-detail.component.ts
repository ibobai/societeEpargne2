import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransfert } from 'app/shared/model/transfert.model';

@Component({
  selector: 'jhi-transfert-detail',
  templateUrl: './transfert-detail.component.html',
})
export class TransfertDetailComponent implements OnInit {
  transfert: ITransfert | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transfert }) => (this.transfert = transfert));
  }

  previousState(): void {
    window.history.back();
  }
}
