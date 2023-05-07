import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuReportesMedicosComponent } from './menu-reportes-medicos.component';

describe('MenuReportesMedicosComponent', () => {
  let component: MenuReportesMedicosComponent;
  let fixture: ComponentFixture<MenuReportesMedicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuReportesMedicosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuReportesMedicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
