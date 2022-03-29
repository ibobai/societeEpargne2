import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITransfert, Transfert } from 'app/shared/model/transfert.model';
import { TransfertService } from './transfert.service';
import { ICompte } from 'app/shared/model/compte.model';
import { CompteService } from 'app/entities/compte/compte.service';

@Component({
  selector: 'jhi-transfert-update',
  templateUrl: './transfert-update.component.html',
})
export class TransfertUpdateComponent implements OnInit {
  isSaving = false;
  comptes: ICompte[] = [];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    transfertid: [null, [Validators.required]],
    date: [],
    montant: [null, [Validators.required]],
    typeoperation: [null, [Validators.required, Validators.maxLength(10)]],
    numcompte: [null, [Validators.required]],
    conseillerid: [null, [Validators.required]],
    compteId: [],
  });

  constructor(
    protected transfertService: TransfertService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transfert }) => {
      this.updateForm(transfert);

      this.compteService.query().subscribe((res: HttpResponse<ICompte[]>) => (this.comptes = res.body || []));
    });
  }

  updateForm(transfert: ITransfert): void {
    this.editForm.patchValue({
      id: transfert.id,
      transfertid: transfert.transfertid,
      date: transfert.date,
      montant: transfert.montant,
      typeoperation: transfert.typeoperation,
      numcompte: transfert.numcompte,
      conseillerid: transfert.conseillerid,
      compteId: transfert.compteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transfert = this.createFromForm();
    if (transfert.id !== undefined) {
      this.subscribeToSaveResponse(this.transfertService.update(transfert));
    } else {
      this.subscribeToSaveResponse(this.transfertService.create(transfert));
    }
  }

  private createFromForm(): ITransfert {
    return {
      ...new Transfert(),
      id: this.editForm.get(['id'])!.value,
      transfertid: this.editForm.get(['transfertid'])!.value,
      date: this.editForm.get(['date'])!.value,
      montant: this.editForm.get(['montant'])!.value,
      typeoperation: this.editForm.get(['typeoperation'])!.value,
      numcompte: this.editForm.get(['numcompte'])!.value,
      conseillerid: this.editForm.get(['conseillerid'])!.value,
      compteId: this.editForm.get(['compteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransfert>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICompte): any {
    return item.id;
  }
}
