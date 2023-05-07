import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoMenuHistorialComponent } from './medico-menu-historial.component';

describe('MedicoMenuHistorialComponent', () => {
  let component: MedicoMenuHistorialComponent;
  let fixture: ComponentFixture<MedicoMenuHistorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoMenuHistorialComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicoMenuHistorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
