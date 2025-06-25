import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-revieworderedproduct',
  templateUrl: './revieworderedproduct.component.html',
  styleUrls: ['./revieworderedproduct.component.scss']
})
export class RevieworderedproductComponent {

  productId: number = this.activatedRoute.snapshot.params['productId'];
  reviewForm: FormGroup;
  selectedFile: File | null;
  imagePerview: String | ArrayBuffer | null;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private customerService: CustomerService
  ){}


  ngOnInit(){
    this.reviewForm = this.fb.group({
      rating: [null,[Validators.required]],
      description: [null,[Validators.required]]
    })
  }

  onFileSelected(event: any){
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePerview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  submitForm(){
    const formData: FormData = new FormData();
    formData.append('retunedImg',this.selectedFile);
    formData.append('product_id', this.productId.toString());
    formData.append('user_id', UserStorageService.getUserId().toString());
    formData.append('rating', this.reviewForm.get('rating').value);
    formData.append('description', this.reviewForm.get('description').value);


    this.customerService.postReview(formData).subscribe(res =>{
      if(res.id != null){
        this.snackBar.open("Review Posted Successfully","Close", {duration: 5000});
        this.router.navigateByUrl("customer/my_orders");
      }else{
        this.snackBar.open("Something went wrong","ERROR", {duration: 5000});
      }
    })
  }

}
