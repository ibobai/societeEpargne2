import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICompte, Compte } from 'app/shared/model/compte.model';
import { CompteService } from './compte.service';

@Component({
  selector: 'jhi-compte-update',
  templateUrl: './compte-update.component.html',
})
export class CompteUpdateComponent implements OnInit {
  isSaving = false;
  comptes: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    compteid: [null, [Validators.required]],
    numcom: [null, [Validators.required]],
    solde: [null, [Validators.required]],
    clientid: [null, [Validators.required]],
    compteId: [],
  });

  constructor(protected compteService: CompteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ compte }) => {
      this.updateForm(compte);

      this.compteService
        .query({ filter: 'compte-is-null' })
        .pipe(
          map((res: HttpResponse<ICompte[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICompte[]) => {
          if (!compte.compteId) {
            this.comptes = resBody;
          } else {
            this.compteService
              .find(compte.compteId)
              .pipe(
                map((subRes: HttpResponse<ICompte>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICompte[]) => (this.comptes = concatRes));
          }
        });
    });
  }

  updateForm(compte: ICompte): void {
    this.editForm.patchValue({
      id: compte.id,
      compteid: compte.compteid,
      numcom: compte.numcom,
      solde: compte.solde,
      clientid: compte.clientid,
      compteId: compte.compteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const compte = this.createFromForm();
    if (compte.id !== undefined) {
      this.subscribeToSaveResponse(this.compteService.update(compte));
    } else {
      this.subscribeToSaveResponse(this.compteService.create(compte));
    }
  }

  private createFromForm(): ICompte {
    return {
      ...new Compte(),
      id: this.editForm.get(['id'])!.value,
      compteid: this.editForm.get(['compteid'])!.value,
      numcom: this.editForm.get(['numcom'])!.value,
      solde: this.editForm.get(['solde'])!.value,
      clientid: this.editForm.get(['clientid'])!.value,
      compteId: this.editForm.get(['compteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompte>>): void {
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
