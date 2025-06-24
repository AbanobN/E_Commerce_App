import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevieworderedproductComponent } from './revieworderedproduct.component';

describe('RevieworderedproductComponent', () => {
  let component: RevieworderedproductComponent;
  let fixture: ComponentFixture<RevieworderedproductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RevieworderedproductComponent]
    });
    fixture = TestBed.createComponent(RevieworderedproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
