import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAgregarExamenesSolicitudPacientesComponent } from './modal-agregar-examenes-solicitud-pacientes.component';

describe('ModalAgregarExamenesSolicitudPacientesComponent', () => {
  let component: ModalAgregarExamenesSolicitudPacientesComponent;
  let fixture: ComponentFixture<ModalAgregarExamenesSolicitudPacientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAgregarExamenesSolicitudPacientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAgregarExamenesSolicitudPacientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
