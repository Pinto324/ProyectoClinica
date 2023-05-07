import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSolicitarConsultaComponent } from './menu-solicitar-consulta.component';

describe('MenuSolicitarConsultaComponent', () => {
  let component: MenuSolicitarConsultaComponent;
  let fixture: ComponentFixture<MenuSolicitarConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSolicitarConsultaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSolicitarConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
