import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllCoupons(): Observable<any>{
    return this.http.get(BASIC_URL + "api/admin/coupons", {
      headers: this.createAuthorizationHeader()
    })
  }

  addCoupon(couponDto: any): Observable<any>{
    return this.http.post(BASIC_URL + "api/admin/coupons", couponDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  addCategory(categoryDto: any): Observable<any>{
    return this.http.post(BASIC_URL + "api/admin/category", categoryDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllCategories(): Observable<any>{
    return this.http.get(BASIC_URL + "api/admin/categories", {
      headers: this.createAuthorizationHeader()
    })
  }

  addProduct(productDto: any): Observable<any>{
    return this.http.post(BASIC_URL + "api/admin/product", productDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  deleteProduct(productID: any): Observable<any>{
    return this.http.delete(BASIC_URL + `api/admin/product/${productID}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllProductsByName(name: any): Observable<any>{
    return this.http.get(BASIC_URL + `api/admin/search/${name}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllProducts(): Observable<any>{
    return this.http.get(BASIC_URL + "api/admin/products", {
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
