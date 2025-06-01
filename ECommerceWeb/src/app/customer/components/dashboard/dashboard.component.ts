import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

    products: any[] = [];
    searchProductForm: FormGroup;
  
  constructor(
    private customerService: CustomerService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar
    ){}
  
    ngOnInit(){
      this.getAllProducts();
      this.searchProductForm = this.fb.group({
        title: [null, [Validators.required]]
      })
    }
  
    getAllProducts(){
      this.products = [];
      this.customerService.getAllProducts().subscribe(res => {
        res.forEach(element => {
          element.procesedImg = 'data:image/jpeg;base64,'+ element.img;
          this.products.push(element);
        })
      })
    }
  
    submitForm(){
      this.products = [];
      const title = this.searchProductForm.get('title')!.value;
      this.customerService.getAllProductsByName(title).subscribe(res => {
        res.forEach(element => {
          element.procesedImg = 'data:image/jpeg;base64,'+ element.img;
          this.products.push(element);
        })
      })
    }

    addToCart(productId: any) {
      this.customerService.addToCart(productId).subscribe({
          next: (res) => {
              this.snackBar.open('Product added to cart successfully', 'Close', { duration: 5000 });
          },
          error: (err: HttpErrorResponse) => {
            const message = typeof err.error === 'string'
                ? err.error
                : err.error?.message || 'Failed to add product to cart';
            this.snackBar.open(message, 'Close', { duration: 5000 });
        }
      });
  }
}
