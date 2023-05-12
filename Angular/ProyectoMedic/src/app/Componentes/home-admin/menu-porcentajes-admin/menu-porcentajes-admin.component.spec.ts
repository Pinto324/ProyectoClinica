import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPorcentajesAdminComponent } from './menu-porcentajes-admin.component';

describe('MenuPorcentajesAdminComponent', () => {
  let component: MenuPorcentajesAdminComponent;
  let fixture: ComponentFixture<MenuPorcentajesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuPorcentajesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuPorcentajesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
