import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeLaboratoriosComponent } from './home-laboratorios.component';

describe('HomeLaboratoriosComponent', () => {
  let component: HomeLaboratoriosComponent;
  let fixture: ComponentFixture<HomeLaboratoriosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeLaboratoriosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeLaboratoriosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
