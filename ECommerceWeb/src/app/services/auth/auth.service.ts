import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';

const BASIC_URL = "http://localhost:8080/";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private userStorageService : UserStorageService
  ) { }

  register(signupRequest: any): Observable<any> {
    return this.http.post(`${BASIC_URL}sign-up`, signupRequest).pipe(
        tap((user: any) => this.userStorageService.saveUser(user))
    );
}

login(email: string, password: string): Observable<boolean> {
  const headers = new HttpHeaders().set("Content-Type", "application/json");
  const body = { email, password };
  return this.http.post(`${BASIC_URL}authenticate`, body, { headers }).pipe(
      map((res: any) => {
          const token = res.jwt;
          const user = res.user;
          if (token && user) {
              this.userStorageService.saveToken(token);
              this.userStorageService.saveUser(user);
              return true;
          }
          return false;
      })
  );
}

getOrderByTrackingId(trackingId: number): Observable<any>{
  return this.http.get(BASIC_URL + `order/${trackingId}`)
}
}