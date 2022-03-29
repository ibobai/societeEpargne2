import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ICompte } from 'app/shared/model/compte.model';
import { CompteService } from 'app/entities/compte/compte.service';

type SelectableEntity = IUser | ICompte;

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  comptes: ICompte[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required, Validators.maxLength(50)]],
    prenom: [null, [Validators.required, Validators.maxLength(50)]],
    tel: [null, [Validators.required, Validators.maxLength(50)]],
    adresse: [null, [Validators.required, Validators.maxLength(250)]],
    email: [null, [Validators.required, Validators.maxLength(100)]],
    userId: [],
    compteId: [],
  });

  constructor(
    protected clientService: ClientService,
    protected userService: UserService,
    protected compteService: CompteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.compteService
        .query({ filter: 'client-is-null' })
        .pipe(
          map((res: HttpResponse<ICompte[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICompte[]) => {
          if (!client.compteId) {
            this.comptes = resBody;
          } else {
            this.compteService
              .find(client.compteId)
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

  updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      nom: client.nom,
      prenom: client.prenom,
      tel: client.tel,
      adresse: client.adresse,
      email: client.email,
      userId: client.userId,
      compteId: client.compteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      email: this.editForm.get(['email'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      compteId: this.editForm.get(['compteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
