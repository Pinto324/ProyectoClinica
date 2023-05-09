import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuExamenesPendientesComponent } from './menu-examenes-pendientes.component';

describe('MenuExamenesPendientesComponent', () => {
  let component: MenuExamenesPendientesComponent;
  let fixture: ComponentFixture<MenuExamenesPendientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuExamenesPendientesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuExamenesPendientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
