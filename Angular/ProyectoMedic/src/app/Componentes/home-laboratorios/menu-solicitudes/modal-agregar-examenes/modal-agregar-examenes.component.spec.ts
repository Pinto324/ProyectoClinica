import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAgregarExamenesComponent } from './modal-agregar-examenes.component';

describe('ModalAgregarExamenesComponent', () => {
  let component: ModalAgregarExamenesComponent;
  let fixture: ComponentFixture<ModalAgregarExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAgregarExamenesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalAgregarExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
