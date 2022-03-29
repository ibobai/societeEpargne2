import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConseiller, Conseiller } from 'app/shared/model/conseiller.model';
import { ConseillerService } from './conseiller.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-conseiller-update',
  templateUrl: './conseiller-update.component.html',
})
export class ConseillerUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    conseillerid: [null, [Validators.required]],
    nom: [null, [Validators.required, Validators.maxLength(50)]],
    prenom: [null, [Validators.required, Validators.maxLength(50)]],
    tel: [null, [Validators.required, Validators.maxLength(50)]],
    email: [null, [Validators.required, Validators.maxLength(150)]],
    nomutilisateur: [null, [Validators.required, Validators.maxLength(50)]],
    motdepasse: [null, [Validators.required, Validators.maxLength(100)]],
    userId: [],
  });

  constructor(
    protected conseillerService: ConseillerService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ conseiller }) => {
      this.updateForm(conseiller);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(conseiller: IConseiller): void {
    this.editForm.patchValue({
      id: conseiller.id,
      conseillerid: conseiller.conseillerid,
      nom: conseiller.nom,
      prenom: conseiller.prenom,
      tel: conseiller.tel,
      email: conseiller.email,
      nomutilisateur: conseiller.nomutilisateur,
      motdepasse: conseiller.motdepasse,
      userId: conseiller.userId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const conseiller = this.createFromForm();
    if (conseiller.id !== undefined) {
      this.subscribeToSaveResponse(this.conseillerService.update(conseiller));
    } else {
      this.subscribeToSaveResponse(this.conseillerService.create(conseiller));
    }
  }

  private createFromForm(): IConseiller {
    return {
      ...new Conseiller(),
      id: this.editForm.get(['id'])!.value,
      conseillerid: this.editForm.get(['conseillerid'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      email: this.editForm.get(['email'])!.value,
      nomutilisateur: this.editForm.get(['nomutilisateur'])!.value,
      motdepasse: this.editForm.get(['motdepasse'])!.value,
      userId: this.editForm.get(['userId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConseiller>>): void {
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
