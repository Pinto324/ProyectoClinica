import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoMenuEspecialidadesComponent } from './medico-menu-especialidades.component';

describe('MedicoMenuEspecialidadesComponent', () => {
  let component: MedicoMenuEspecialidadesComponent;
  let fixture: ComponentFixture<MedicoMenuEspecialidadesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoMenuEspecialidadesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicoMenuEspecialidadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
