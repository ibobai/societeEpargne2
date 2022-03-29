import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompteepa } from 'app/shared/model/compteepa.model';

@Component({
  selector: 'jhi-compteepa-detail',
  templateUrl: './compteepa-detail.component.html',
})
export class CompteepaDetailComponent implements OnInit {
  compteepa: ICompteepa | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compteepa }) => (this.compteepa = compteepa));
  }

  previousState(): void {
    window.history.back();
  }
}
