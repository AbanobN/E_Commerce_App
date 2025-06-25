import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-product-details',
  templateUrl: './view-product-details.component.html',
  styleUrls: ['./view-product-details.component.scss']
})
export class ViewProductDetailsComponent {

  productId: number = this.activatedRoute.snapshot.params['productId'];

  product: any;
  FAQS: any[] = [];
  reviews: any[] = [];

  constructor(
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute
  ){}

  getProductDetailsById(){
    this.customerService.getProductDetailsById(this.productId).subscribe(res =>{
      if(res != null){
        this.product = res.productDto;
        this.product.processedImg = 'data:image/png;base64,'+ this.product.img;
        
        this.FAQS = res.faqDtoList;
        
        res.reviewDtoList.forEach(element => {
          element.processedImg = 'data:image/png;base64,'+ element.img;
          this.reviews.push(element);
        });
      }
    })
  }

  ngOnInit(){
    this.getProductDetailsById();
  }
}
