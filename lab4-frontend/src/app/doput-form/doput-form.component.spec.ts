import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoPutFormComponent } from './doput-form.component';

describe('DoPutFormComponent', () => {
  let component: DoPutFormComponent;
  let fixture: ComponentFixture<DoPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DoPutFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DoPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
