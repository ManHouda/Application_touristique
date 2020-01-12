import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyCircuitsComponent } from './my-circuits.component';

describe('MyCircuitsComponent', () => {
  let component: MyCircuitsComponent;
  let fixture: ComponentFixture<MyCircuitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyCircuitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyCircuitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
