import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSolicitudesComponent } from './menu-solicitudes.component';

describe('MenuSolicitudesComponent', () => {
  let component: MenuSolicitudesComponent;
  let fixture: ComponentFixture<MenuSolicitudesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSolicitudesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
