import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalPacienteExamenesConsultasComponent } from './modal-paciente-examenes-consultas.component';

describe('ModalPacienteExamenesConsultasComponent', () => {
  let component: ModalPacienteExamenesConsultasComponent;
  let fixture: ComponentFixture<ModalPacienteExamenesConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalPacienteExamenesConsultasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalPacienteExamenesConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
