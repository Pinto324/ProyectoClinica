import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalInformefinalComponent } from './modal-informefinal.component';

describe('ModalInformefinalComponent', () => {
  let component: ModalInformefinalComponent;
  let fixture: ComponentFixture<ModalInformefinalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalInformefinalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalInformefinalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
