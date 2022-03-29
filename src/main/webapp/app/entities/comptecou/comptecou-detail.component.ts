import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComptecou } from 'app/shared/model/comptecou.model';

@Component({
  selector: 'jhi-comptecou-detail',
  templateUrl: './comptecou-detail.component.html',
})
export class ComptecouDetailComponent implements OnInit {
  comptecou: IComptecou | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comptecou }) => (this.comptecou = comptecou));
  }

  previousState(): void {
    window.history.back();
  }
}
