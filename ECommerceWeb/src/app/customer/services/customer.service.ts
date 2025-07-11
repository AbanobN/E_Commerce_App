import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';


const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private http: HttpClient
  ) { }

  getWishListByUserId(): Observable<any>{
  const userId = UserStorageService.getUserId();
  return this.http.get(BASIC_URL + `api/customer/wishlist/${userId}`,{
    headers: this.createAuthorizationHeader()
  });
  }
  
  addProductToWishList(wishListDto: any): Observable<any>{
  return this.http.post(BASIC_URL + `api/customer/wishlist`, wishListDto,{
    headers: this.createAuthorizationHeader()
  });
  }
  
  getProductDetailsById(productId: number): Observable<any>{
  return this.http.get(BASIC_URL + `api/customer/product/${productId}`,{
    headers: this.createAuthorizationHeader()
  });
  }

  postReview(reviewDto: any): Observable<any>{
  return this.http.post(BASIC_URL + `api/customer/review`, reviewDto,{
    headers: this.createAuthorizationHeader()
  });
  }
  
  getOrderedProducts(orderId: number): Observable<any>{
  return this.http.get(BASIC_URL + `api/customer/ordered-products/${orderId}`,{
    headers: this.createAuthorizationHeader()
  });
  }
  
  getOrdersByUserId(): Observable<any>{
    const userId = UserStorageService.getUserId()
  return this.http.get(BASIC_URL + `api/customer/myOrders/${userId}`,{
    headers: this.createAuthorizationHeader()
  });
  }


  placeOrder(orderDto: any): Observable<any>{
    orderDto.userId = UserStorageService.getUserId()
  return this.http.post(BASIC_URL + `api/customer/placedOrder`,orderDto ,{
    headers: this.createAuthorizationHeader()
  });
  }

  decreaseProductQuantity(productId: any): Observable<any>{
    const cartDto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    }
    return this.http.post(BASIC_URL + `api/customer/deduction`, cartDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  increaseProductQuantity(productId: any): Observable<any>{
    const cartDto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    }
    return this.http.post(BASIC_URL + `api/customer/addition`, cartDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  applyCoupon(couponCode: any): Observable<any>{
    const userId = UserStorageService.getUserId()
  return this.http.get(BASIC_URL + `api/customer/coupon/${userId}/${couponCode}`, {
    headers: this.createAuthorizationHeader()
  });
  }

  getCartByUserId(): Observable<any>{
    const userId = UserStorageService.getUserId()
  return this.http.get(BASIC_URL + `api/customer/cart/${userId}`, {
    headers: this.createAuthorizationHeader()
  });
  }

  addToCart(productId: any): Observable<any>{
    const cartDto = {
      productId: productId,
      userId: UserStorageService.getUserId()
    }
    return this.http.post(BASIC_URL + `api/customer/cart`, cartDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  getAllProducts(): Observable<any>{
      return this.http.get(BASIC_URL + "api/customer/products", {
        headers: this.createAuthorizationHeader()
      })
    }
  
    getAllProductsByName(name: any): Observable<any>{
      return this.http.get(BASIC_URL + `api/customer/search/${name}`, {
        headers: this.createAuthorizationHeader()
      })
    }

    private createAuthorizationHeader(): HttpHeaders{
        const token = UserStorageService.getToken();
        return new HttpHeaders().set(
          "Authorization", "Bearer " + UserStorageService.getToken()
        )
      }
}
