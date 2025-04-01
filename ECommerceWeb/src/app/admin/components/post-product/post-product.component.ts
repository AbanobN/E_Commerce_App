import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent implements OnInit{

  productForm: FormGroup;
  listOfCategories: any = [];
  selectedFile: File | null;
  imagePreview:  string | ArrayBuffer | null;

  ngOnInit(): void {
        this.productForm = this.fb.group({
          categoryId: [null,[Validators.required]],
          name: [null,[Validators.required]],
          price: [null,[Validators.required]],
          description: [null,[Validators.required]]
        })

        this.getAllCategories();
    }

    getAllCategories(){
      this.adminService.getAllCategories().subscribe(
        (res) => {
          this.listOfCategories = res;
        }
      )
    }

    addProduct(): void {
      if (this.productForm.valid && this.selectedFile) {
        const formData: FormData = new FormData();
        formData.append('img', this.selectedFile);
        formData.append('categoryId', this.productForm.get('categoryId')!.value.toString());
        formData.append('name', this.productForm.get('name')!.value);
        formData.append('price', this.productForm.get('price')!.value.toString());
        formData.append('description', this.productForm.get('description')!.value);
  
        this.adminService.addProduct(formData).subscribe({
          next: (res) => {
            if (res.id) {
              this.snackBar.open('Product Posted Successfully!', 'Close', { duration: 5000 });
              this.router.navigateByUrl("/admin/dashboard");
            } else {
              this.snackBar.open('Product creation failed', 'ERROR', { duration: 5000 });
            }
          },
          error: (err) => {
            this.snackBar.open(err.error?.message || 'Error posting product', 'ERROR', { duration: 5000 });
          }
        });
      } else {
        this.snackBar.open('Please fill all fields and select an image', 'ERROR', { duration: 5000 });
        for (const i in this.productForm.controls) {
          this.productForm.controls[i].markAsDirty();
          this.productForm.controls[i].updateValueAndValidity();
        }
      }
    }

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ){}

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () =>{
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }


}
