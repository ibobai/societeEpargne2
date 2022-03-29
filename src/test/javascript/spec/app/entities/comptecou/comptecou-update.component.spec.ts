import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { ComptecouUpdateComponent } from 'app/entities/comptecou/comptecou-update.component';
import { ComptecouService } from 'app/entities/comptecou/comptecou.service';
import { Comptecou } from 'app/shared/model/comptecou.model';

describe('Component Tests', () => {
  describe('Comptecou Management Update Component', () => {
    let comp: ComptecouUpdateComponent;
    let fixture: ComponentFixture<ComptecouUpdateComponent>;
    let service: ComptecouService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [ComptecouUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ComptecouUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ComptecouUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ComptecouService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Comptecou(123);
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
        const entity = new Comptecou();
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
