import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuExamenesComponent } from './menu-examenes.component';

describe('MenuExamenesComponent', () => {
  let component: MenuExamenesComponent;
  let fixture: ComponentFixture<MenuExamenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuExamenesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuExamenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
