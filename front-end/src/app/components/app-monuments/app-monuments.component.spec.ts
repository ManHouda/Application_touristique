import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppMonumentsComponent } from './app-monuments.component';

describe('AppMonumentsComponent', () => {
  let component: AppMonumentsComponent;
  let fixture: ComponentFixture<AppMonumentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppMonumentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppMonumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
