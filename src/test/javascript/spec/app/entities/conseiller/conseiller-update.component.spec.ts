import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { ConseillerUpdateComponent } from 'app/entities/conseiller/conseiller-update.component';
import { ConseillerService } from 'app/entities/conseiller/conseiller.service';
import { Conseiller } from 'app/shared/model/conseiller.model';

describe('Component Tests', () => {
  describe('Conseiller Management Update Component', () => {
    let comp: ConseillerUpdateComponent;
    let fixture: ComponentFixture<ConseillerUpdateComponent>;
    let service: ConseillerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [ConseillerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConseillerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConseillerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConseillerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Conseiller(123);
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
        const entity = new Conseiller();
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
