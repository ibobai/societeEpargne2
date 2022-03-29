import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { ConseillerDetailComponent } from 'app/entities/conseiller/conseiller-detail.component';
import { Conseiller } from 'app/shared/model/conseiller.model';

describe('Component Tests', () => {
  describe('Conseiller Management Detail Component', () => {
    let comp: ConseillerDetailComponent;
    let fixture: ComponentFixture<ConseillerDetailComponent>;
    const route = ({ data: of({ conseiller: new Conseiller(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [ConseillerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConseillerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConseillerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load conseiller on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.conseiller).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
