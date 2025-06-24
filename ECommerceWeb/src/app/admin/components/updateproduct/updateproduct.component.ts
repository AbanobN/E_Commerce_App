import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.scss']
})
export class UpdateproductComponent {
    productId =  this.activatedRoute.snapshot.params["productId"];
    productForm: FormGroup;
    listOfCategories: any = [];
    selectedFile: File | null;
    imagePreview:  string | ArrayBuffer | null;
    existingImage: string | null = null; 
    isImageChanged = false;
    
    

    constructor(
      private fb: FormBuilder,
      private router: Router,
      private snackBar: MatSnackBar,
      private adminService: AdminService,
      private activatedRoute: ActivatedRoute
    ){}

    getProductById(){
      this.adminService.getProductById(this.productId).subscribe(res =>{
        this.productForm.patchValue(res);
        this.existingImage = 'data:image/jpeg;base64,' + res.img;
      })
    }
  
    ngOnInit(): void {
          this.productForm = this.fb.group({
            categoryId: [null,[Validators.required]],
            name: [null,[Validators.required]],
            price: [null,[Validators.required]],
            description: [null,[Validators.required]]
          })
  
          this.getAllCategories();
          this.getProductById();
      }
  
      getAllCategories(){
        this.adminService.getAllCategories().subscribe(
          (res) => {
            this.listOfCategories = res;
          }
        )
      }
  
      updateProduct(): void {
        if (this.productForm.valid) {
          const formData: FormData = new FormData();

          if(this.isImageChanged && this.selectedFile){
            formData.append('img', this.selectedFile);
          }

          
          formData.append('categoryId', this.productForm.get('categoryId')!.value.toString());
          formData.append('name', this.productForm.get('name')!.value);
          formData.append('price', this.productForm.get('price')!.value.toString());
          formData.append('description', this.productForm.get('description')!.value);
    
          this.adminService.upateProduct(this.productId ,formData).subscribe({
            next: (res) => {
              if (res.id) {
                this.snackBar.open('Product Updated Successfully!', 'Close', { duration: 5000 });
                this.router.navigateByUrl("/admin/dashboard");
              } else {
                this.snackBar.open('Product creation failed', 'ERROR', { duration: 5000 });
              }
            },
            error: (err) => {
              this.snackBar.open(err.error?.message || 'Error Updating product', 'ERROR', { duration: 5000 });
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
  
    onFileSelected(event: any){
      this.selectedFile = event.target.files[0];
      this.previewImage();
      this.isImageChanged = true;
      this.existingImage = null;
    }
  
    previewImage(){
      const reader = new FileReader();
      reader.onload = () =>{
        this.imagePreview = reader.result;
      }
      reader.readAsDataURL(this.selectedFile);
    }
  
  
}
