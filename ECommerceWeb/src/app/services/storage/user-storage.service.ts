import { Injectable } from '@angular/core';

const TOKEN = "ecom-token";
const USER = "ecom-user";

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  static getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  static getUser(): any {
    const userData = localStorage.getItem(USER);
    if (!userData) {
      return null;
    }
    try {
      return JSON.parse(userData);
    } catch (e) {
      console.error("Error parsing user data from localStorage:", e);
      return null;
    }
  }

  static getUserId(): string {
    const user = this.getUser();
    if (!user) {
      return '';
    }
    return user.id || '';
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (!user) {
      return '';
    }
    return user.userRole || ''; 
  }

  static isAdminLoggedIn(): boolean {
    if (!this.getToken()) {
      return false;
    }
    const role = this.getUserRole();
    return role === "ADMIN";
  }

  static isCustomerLoggedIn(): boolean {
    if (!this.getToken()) {
      return false;
    }
    const role = this.getUserRole();
    return role === "CUSTOMER";
  }

  static signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}