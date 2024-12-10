import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from './app.component';
import {CustomerComponent} from './components/customer/customer.component';

const routes: Routes = [
  { path: '', component: AppComponent }, // Home Page
  { path: 'customer', component: CustomerComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
