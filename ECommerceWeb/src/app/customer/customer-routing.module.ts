import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CartComponent } from './components/cart/cart.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';
import { VieworderedProductsComponent } from './components/viewordered-products/viewordered-products.component';
import { RevieworderedproductComponent } from './components/revieworderedproduct/revieworderedproduct.component';
import { ViewProductDetailsComponent } from './components/view-product-details/view-product-details.component';
import { ViewWishlistComponent } from './components/view-wishlist/view-wishlist.component';

const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'cart', component: CartComponent },
  { path: 'my_orders', component: MyOrdersComponent },
  { path: 'ordered_products/:orderId', component: VieworderedProductsComponent },
  { path: 'review/:productId', component: RevieworderedproductComponent },
  { path: 'product/:productId', component: ViewProductDetailsComponent },
  { path: 'wishlist', component: ViewWishlistComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
