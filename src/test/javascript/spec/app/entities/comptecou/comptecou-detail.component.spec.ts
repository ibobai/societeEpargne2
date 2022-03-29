import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { ComptecouDetailComponent } from 'app/entities/comptecou/comptecou-detail.component';
import { Comptecou } from 'app/shared/model/comptecou.model';

describe('Component Tests', () => {
  describe('Comptecou Management Detail Component', () => {
    let comp: ComptecouDetailComponent;
    let fixture: ComponentFixture<ComptecouDetailComponent>;
    const route = ({ data: of({ comptecou: new Comptecou(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [ComptecouDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ComptecouDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ComptecouDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load comptecou on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.comptecou).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
