import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompteepa, Compteepa } from 'app/shared/model/compteepa.model';
import { CompteepaService } from './compteepa.service';
import { ICompte } from 'app/shared/model/compte.model';
import { CompteService } from 'app/entities/compte/compte.service';

@Component({
  selector: 'jhi-compteepa-update',
  templateUrl: './compteepa-update.component.html',
})
export class CompteepaUpdateComponent implements OnInit {
  isSaving = false;
  comptes: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    tauxinteret: [null, [Validators.required]],
    plafond: [null, [Validators.required]],
    compteId: [],
  });

  constructor(
    protected compteepaService: CompteepaService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compteepa }) => {
      this.updateForm(compteepa);

      this.compteService.query().subscribe((res: HttpResponse<ICompte[]>) => (this.comptes = res.body || []));
    });
  }

  updateForm(compteepa: ICompteepa): void {
    this.editForm.patchValue({
      id: compteepa.id,
      tauxinteret: compteepa.tauxinteret,
      plafond: compteepa.plafond,
      compteId: compteepa.compteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compteepa = this.createFromForm();
    if (compteepa.id !== undefined) {
      this.subscribeToSaveResponse(this.compteepaService.update(compteepa));
    } else {
      this.subscribeToSaveResponse(this.compteepaService.create(compteepa));
    }
  }

  private createFromForm(): ICompteepa {
    return {
      ...new Compteepa(),
      id: this.editForm.get(['id'])!.value,
      tauxinteret: this.editForm.get(['tauxinteret'])!.value,
      plafond: this.editForm.get(['plafond'])!.value,
      compteId: this.editForm.get(['compteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompteepa>>): void {
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
