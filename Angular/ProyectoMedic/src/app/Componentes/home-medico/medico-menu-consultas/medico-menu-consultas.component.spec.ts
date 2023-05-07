import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoMenuConsultasComponent } from './medico-menu-consultas.component';

describe('MedicoMenuConsultasComponent', () => {
  let component: MedicoMenuConsultasComponent;
  let fixture: ComponentFixture<MedicoMenuConsultasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoMenuConsultasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicoMenuConsultasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
