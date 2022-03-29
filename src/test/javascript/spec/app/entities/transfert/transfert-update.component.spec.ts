import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { TransfertUpdateComponent } from 'app/entities/transfert/transfert-update.component';
import { TransfertService } from 'app/entities/transfert/transfert.service';
import { Transfert } from 'app/shared/model/transfert.model';

describe('Component Tests', () => {
  describe('Transfert Management Update Component', () => {
    let comp: TransfertUpdateComponent;
    let fixture: ComponentFixture<TransfertUpdateComponent>;
    let service: TransfertService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [TransfertUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TransfertUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransfertUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransfertService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Transfert(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Transfert();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
