import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketPoolComponent } from './components/ticket-pool/ticket-pool.component';
import { VendorComponent } from './components/vendor/vendor.component';
import {AppComponent} from './app.component';
import {CustomerComponent} from './components/customer/customer.component';

const routes: Routes = [
  { path: '', component: AppComponent }, // Home Page
  { path: 'ticket-pool', component: TicketPoolComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'vendor', component: VendorComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' } // Fallback route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
