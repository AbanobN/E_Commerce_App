import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserStorgeService } from '../storge/user-storge.service';

const BASIC_URL = "http://localhost:8080/";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private userStorageService : UserStorgeService
  ) { }

  register(signupRequest: any) : Observable<any>{
    return this.http.post(BASIC_URL + "sign-up" , signupRequest);
  }

  login(userEmail: string, userPassword: string): Observable<boolean> {
    const headers = new HttpHeaders().set("Content-Type", "application/json");
    const body = { userName: userEmail, password: userPassword };
  
    return this.http.post(BASIC_URL + "authenticate", body, { headers, observe: "response" }).pipe(
      map((res) => {
        const token = res.headers.get("authorization").substring(7);
        const user = res.body;
        if (token && user) {
          this.userStorageService.saveToken(token);
          this.userStorageService.saveUser(user);
          return true;
        }
        return false;
      })
    );
  }
  
}
