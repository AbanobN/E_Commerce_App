import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-viewordered-products',
  templateUrl: './viewordered-products.component.html',
  styleUrls: ['./viewordered-products.component.scss']
})
export class VieworderedProductsComponent {

  orderId: any = this.activatedRoute.snapshot.params['orderId'];
  orderedProductDetailsList = [];
  totalAmout: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private customerService: CustomerService
  ){}

  ngOnInit(){
    this.getOrderedProductsDetailsByOrderId();
  }

  getOrderedProductsDetailsByOrderId(){
    this.customerService.getOrderedProducts(this.orderId).subscribe(res =>{
      res.productDtoList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.img;
        this.orderedProductDetailsList.push(element);
      });
      this.totalAmout = res.orderAmount;
    })
  }
}
