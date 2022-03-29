import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { TransfertDetailComponent } from 'app/entities/transfert/transfert-detail.component';
import { Transfert } from 'app/shared/model/transfert.model';

describe('Component Tests', () => {
  describe('Transfert Management Detail Component', () => {
    let comp: TransfertDetailComponent;
    let fixture: ComponentFixture<TransfertDetailComponent>;
    const route = ({ data: of({ transfert: new Transfert(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [TransfertDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TransfertDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TransfertDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load transfert on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.transfert).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
