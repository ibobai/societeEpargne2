import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComptecou, Comptecou } from 'app/shared/model/comptecou.model';
import { ComptecouService } from './comptecou.service';
import { ICompte } from 'app/shared/model/compte.model';
import { CompteService } from 'app/entities/compte/compte.service';

@Component({
  selector: 'jhi-comptecou-update',
  templateUrl: './comptecou-update.component.html',
})
export class ComptecouUpdateComponent implements OnInit {
  isSaving = false;
  comptes: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    fraistrans: [null, [Validators.required]],
    soldemin: [null, [Validators.required]],
    compteId: [],
  });

  constructor(
    protected comptecouService: ComptecouService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comptecou }) => {
      this.updateForm(comptecou);

      this.compteService.query().subscribe((res: HttpResponse<ICompte[]>) => (this.comptes = res.body || []));
    });
  }

  updateForm(comptecou: IComptecou): void {
    this.editForm.patchValue({
      id: comptecou.id,
      fraistrans: comptecou.fraistrans,
      soldemin: comptecou.soldemin,
      compteId: comptecou.compteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comptecou = this.createFromForm();
    if (comptecou.id !== undefined) {
      this.subscribeToSaveResponse(this.comptecouService.update(comptecou));
    } else {
      this.subscribeToSaveResponse(this.comptecouService.create(comptecou));
    }
  }

  private createFromForm(): IComptecou {
    return {
      ...new Comptecou(),
      id: this.editForm.get(['id'])!.value,
      fraistrans: this.editForm.get(['fraistrans'])!.value,
      soldemin: this.editForm.get(['soldemin'])!.value,
      compteId: this.editForm.get(['compteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComptecou>>): void {
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
