import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  
  products: any[] = [];
  searchProductForm: FormGroup;

constructor(
  private adminService: AdminService,
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
    this.adminService.getAllProducts().subscribe(res => {
      res.forEach(element => {
        element.procesedImg = 'data:image/jpeg;base64,'+ element.img;
        this.products.push(element);
      })
    })
  }

  submitForm(){
    this.products = [];
    const title = this.searchProductForm.get('title')!.value;
    this.adminService.getAllProductsByName(title).subscribe(res => {
      res.forEach(element => {
        element.procesedImg = 'data:image/jpeg;base64,'+ element.img;
        this.products.push(element);
      })
    })
  }

  deleteProduct(productID: any){
    this.adminService.deleteProduct(productID).subscribe(res =>{
      if(!res || res.body == null){
        this.snackBar.open('Product Posted Deleted!', 'Close', { duration: 5000 });
        this.getAllProducts();
      }else{
        this.snackBar.open(res.message, 'Close' , {
          duration: 5000,
          panelClass: "error-snackbar"
        });
      }
    })
  }
}
