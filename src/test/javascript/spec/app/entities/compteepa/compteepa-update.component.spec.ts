import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { CompteepaUpdateComponent } from 'app/entities/compteepa/compteepa-update.component';
import { CompteepaService } from 'app/entities/compteepa/compteepa.service';
import { Compteepa } from 'app/shared/model/compteepa.model';

describe('Component Tests', () => {
  describe('Compteepa Management Update Component', () => {
    let comp: CompteepaUpdateComponent;
    let fixture: ComponentFixture<CompteepaUpdateComponent>;
    let service: CompteepaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [CompteepaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CompteepaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompteepaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompteepaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Compteepa(123);
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
        const entity = new Compteepa();
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
