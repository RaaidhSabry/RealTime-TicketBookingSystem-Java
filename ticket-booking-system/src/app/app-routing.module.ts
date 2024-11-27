import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketPoolComponent } from './components/ticket-pool/ticket-pool.component';
import { VendorComponent } from './components/vendor/vendor.component';

const routes: Routes = [
  { path: 'ticket-pool', component: TicketPoolComponent },
  { path: 'vendor', component: VendorComponent },
  { path: '', redirectTo: 'ticket-pool', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
