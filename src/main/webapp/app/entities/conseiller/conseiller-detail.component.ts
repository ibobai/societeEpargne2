import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConseiller } from 'app/shared/model/conseiller.model';

@Component({
  selector: 'jhi-conseiller-detail',
  templateUrl: './conseiller-detail.component.html',
})
export class ConseillerDetailComponent implements OnInit {
  conseiller: IConseiller | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conseiller }) => (this.conseiller = conseiller));
  }

  previousState(): void {
    window.history.back();
  }
}
