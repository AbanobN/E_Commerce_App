import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemoAngularMaterialModule } from '../DemoAngularMaterialModule';


@NgModule({
  declarations: [
    CustomerComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DemoAngularMaterialModule
  ]
})
export class CustomerModule { }
