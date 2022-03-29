import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { CompteepaDetailComponent } from 'app/entities/compteepa/compteepa-detail.component';
import { Compteepa } from 'app/shared/model/compteepa.model';

describe('Component Tests', () => {
  describe('Compteepa Management Detail Component', () => {
    let comp: CompteepaDetailComponent;
    let fixture: ComponentFixture<CompteepaDetailComponent>;
    const route = ({ data: of({ compteepa: new Compteepa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [CompteepaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CompteepaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompteepaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load compteepa on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.compteepa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
