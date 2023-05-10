import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuReportePacienteComponent } from './menu-reporte-paciente.component';

describe('MenuReportePacienteComponent', () => {
  let component: MenuReportePacienteComponent;
  let fixture: ComponentFixture<MenuReportePacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuReportePacienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuReportePacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
