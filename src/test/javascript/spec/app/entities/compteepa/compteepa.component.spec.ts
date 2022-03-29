import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { BriefGroupeJHipsterTestModule } from '../../../test.module';
import { CompteepaComponent } from 'app/entities/compteepa/compteepa.component';
import { CompteepaService } from 'app/entities/compteepa/compteepa.service';
import { Compteepa } from 'app/shared/model/compteepa.model';

describe('Component Tests', () => {
  describe('Compteepa Management Component', () => {
    let comp: CompteepaComponent;
    let fixture: ComponentFixture<CompteepaComponent>;
    let service: CompteepaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BriefGroupeJHipsterTestModule],
        declarations: [CompteepaComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(CompteepaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompteepaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompteepaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Compteepa(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.compteepas && comp.compteepas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Compteepa(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.compteepas && comp.compteepas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
