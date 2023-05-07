import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAgregarExamenesConsultaComponent } from './modal-agregar-examenes-consulta.component';

describe('ModalAgregarExamenesConsultaComponent', () => {
  let component: ModalAgregarExamenesConsultaComponent;
  let fixture: ComponentFixture<ModalAgregarExamenesConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAgregarExamenesConsultaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAgregarExamenesConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
