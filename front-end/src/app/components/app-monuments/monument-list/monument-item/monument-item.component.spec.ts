import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonumentItemComponent } from './monument-item.component';

describe('MonumentItemComponent', () => {
  let component: MonumentItemComponent;
  let fixture: ComponentFixture<MonumentItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonumentItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonumentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
