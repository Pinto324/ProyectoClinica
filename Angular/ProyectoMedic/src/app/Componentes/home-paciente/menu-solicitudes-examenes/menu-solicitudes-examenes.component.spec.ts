import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSolicitudesExamenesComponent } from './menu-solicitudes-examenes.component';

describe('MenuSolicitudesExamenesComponent', () => {
  let component: MenuSolicitudesExamenesComponent;
  let fixture: ComponentFixture<MenuSolicitudesExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSolicitudesExamenesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSolicitudesExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
