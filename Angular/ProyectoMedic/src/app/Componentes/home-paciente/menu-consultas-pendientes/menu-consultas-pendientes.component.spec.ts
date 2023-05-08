import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuConsultasPendientesComponent } from './menu-consultas-pendientes.component';

describe('MenuConsultasPendientesComponent', () => {
  let component: MenuConsultasPendientesComponent;
  let fixture: ComponentFixture<MenuConsultasPendientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuConsultasPendientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuConsultasPendientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
