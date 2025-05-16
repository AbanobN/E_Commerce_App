import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';

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

    addToCard(productId: any){

    }
}
