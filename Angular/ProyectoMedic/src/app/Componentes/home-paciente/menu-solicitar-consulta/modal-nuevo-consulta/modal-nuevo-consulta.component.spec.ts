import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalNuevoConsultaComponent } from './modal-nuevo-consulta.component';

describe('ModalNuevoConsultaComponent', () => {
  let component: ModalNuevoConsultaComponent;
  let fixture: ComponentFixture<ModalNuevoConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalNuevoConsultaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalNuevoConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
