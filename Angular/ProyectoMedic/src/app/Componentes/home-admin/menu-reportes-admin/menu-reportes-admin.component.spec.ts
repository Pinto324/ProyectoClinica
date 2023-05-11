import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuReportesAdminComponent } from './menu-reportes-admin.component';

describe('MenuReportesAdminComponent', () => {
  let component: MenuReportesAdminComponent;
  let fixture: ComponentFixture<MenuReportesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuReportesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuReportesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
