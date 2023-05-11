import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuEspecialidadesAdminComponent } from './menu-especialidades-admin.component';

describe('MenuEspecialidadesAdminComponent', () => {
  let component: MenuEspecialidadesAdminComponent;
  let fixture: ComponentFixture<MenuEspecialidadesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuEspecialidadesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuEspecialidadesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
