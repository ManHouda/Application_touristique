import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppCircuitComponent } from './app-circuit.component';

describe('AppCircuitComponent', () => {
  let component: AppCircuitComponent;
  let fixture: ComponentFixture<AppCircuitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppCircuitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppCircuitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
