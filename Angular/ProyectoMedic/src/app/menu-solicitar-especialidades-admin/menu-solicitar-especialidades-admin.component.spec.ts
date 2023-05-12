import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSolicitarEspecialidadesAdminComponent } from './menu-solicitar-especialidades-admin.component';

describe('MenuSolicitarEspecialidadesAdminComponent', () => {
  let component: MenuSolicitarEspecialidadesAdminComponent;
  let fixture: ComponentFixture<MenuSolicitarEspecialidadesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSolicitarEspecialidadesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSolicitarEspecialidadesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
