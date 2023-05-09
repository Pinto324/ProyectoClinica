import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalExamenesPendientesPacientesComponent } from './modal-examenes-pendientes-pacientes.component';

describe('ModalExamenesPendientesPacientesComponent', () => {
  let component: ModalExamenesPendientesPacientesComponent;
  let fixture: ComponentFixture<ModalExamenesPendientesPacientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalExamenesPendientesPacientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalExamenesPendientesPacientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
