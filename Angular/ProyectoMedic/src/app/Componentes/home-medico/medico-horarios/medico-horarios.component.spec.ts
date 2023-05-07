import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoHorariosComponent } from './medico-horarios.component';

describe('MedicoHorariosComponent', () => {
  let component: MedicoHorariosComponent;
  let fixture: ComponentFixture<MedicoHorariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicoHorariosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicoHorariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
