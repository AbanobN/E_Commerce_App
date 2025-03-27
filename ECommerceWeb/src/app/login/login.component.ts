import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service'; // Fixed typo in path

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{

  loginForm: FormGroup; 
  hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackbar: MatSnackBar,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    });
  }


  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
        this.snackbar.open("Please fill in all required fields correctly", "ERROR", { duration: 5000 });
        return;
    }

    const email = this.loginForm.get("email")!.value;
    const password = this.loginForm.get("password")!.value;

    this.authService.login(email, password).subscribe({
        next: (success) => {
            console.log("Login success:", success);
            if (success) {
                const isAdmin = UserStorageService.isAdminLoggedIn();
                const isCustomer = UserStorageService.isCustomerLoggedIn();
                console.log("Is Admin:", isAdmin, "Is Customer:", isCustomer);
                if (isAdmin) {
                    this.router.navigateByUrl("admin/dashboard");
                } else if (isCustomer) {
                    this.router.navigateByUrl("customer/dashboard");
                } else {
                    this.snackbar.open("Unknown user role", "ERROR", { duration: 5000 });
                }
            } else {
                this.snackbar.open("Login failed", "ERROR", { duration: 5000 });
            }
        },
        error: (err) => {
            const message = err.status === 401 ? "Bad Credentials" : "Login error, please try again";
            this.snackbar.open(message, "ERROR", { duration: 5000 });
        }
    });
}
}