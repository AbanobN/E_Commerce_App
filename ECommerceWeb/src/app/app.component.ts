import { Component, OnInit } from '@angular/core';
import { UserStorgeService } from './services/storge/user-storge.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'ECommerceWeb';
  isCustomerLoggedIn : boolean = UserStorgeService.isCustomerLoggedIn();
  isAdminLoggedIn : boolean = UserStorgeService.isAdminLoggedIn();

  constructor(private router: Router){}

  ngOnInit() : void{
    this.router.events.subscribe(
      event => {
        this.isCustomerLoggedIn = UserStorgeService.isCustomerLoggedIn();
        this.isAdminLoggedIn = UserStorgeService.isAdminLoggedIn();
      }
    )
  }

  logout() {
    UserStorgeService.signOut();
    this.router.navigateByUrl("/login");
  }
}
