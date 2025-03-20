import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorgeService } from '../services/storge/user-storge.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent  implements OnInit {

  loginForm! : FormGroup;
  hidePassword = true; 

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  constructor(
    private formBuilder : FormBuilder,
    private authService : AuthService,
    private snackbar : MatSnackBar,
    private router : Router
  ){}

  ngOnInit(): void{
    this.loginForm = this.formBuilder.group({
      email: [null , [Validators.required]],
      password: [null , [Validators.required]],
    })
  }

  onSubmit(): void{
    const userEmail = this.loginForm.get("email").value;
    const userPassword = this.loginForm.get("password")?.value;

    this.authService.login(userEmail,userPassword).subscribe(
      (res) => {
        if(UserStorgeService.isAdminLoggedIn()){
          this.router.navigateByUrl("admin/dashboard");
        }
        else if(UserStorgeService.isCustomerLoggedIn()){
          this.router.navigateByUrl("customer/dashboard");
        }
      },
      (err) =>{
        this.snackbar.open("Bad Credentials" , "ERROR", {duration: 5000});
      }
    )
  }

}
