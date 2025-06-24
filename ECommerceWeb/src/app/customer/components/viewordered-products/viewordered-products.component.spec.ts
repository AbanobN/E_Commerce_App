import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VieworderedProductsComponent } from './viewordered-products.component';

describe('VieworderedProductsComponent', () => {
  let component: VieworderedProductsComponent;
  let fixture: ComponentFixture<VieworderedProductsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VieworderedProductsComponent]
    });
    fixture = TestBed.createComponent(VieworderedProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
