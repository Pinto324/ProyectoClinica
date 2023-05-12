import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuSolicitarExamenesAdminComponent } from './menu-solicitar-examenes-admin.component';

describe('MenuSolicitarExamenesAdminComponent', () => {
  let component: MenuSolicitarExamenesAdminComponent;
  let fixture: ComponentFixture<MenuSolicitarExamenesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuSolicitarExamenesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuSolicitarExamenesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
