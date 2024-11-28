import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { TicketPoolComponent } from './components/ticket-pool/ticket-pool.component';
import { VendorComponent } from './components/vendor/vendor.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';


import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {SignupComponent} from './components/signup/signup.component';
import {LoginComponent} from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    TicketPoolComponent,  // Declare all your components here
    VendorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    TicketPoolComponent,
    SignupComponent,
    LoginComponent,
    RouterModule,
    RouterModule,
    TicketPoolComponent,
  ],
  providers: [],
  bootstrap: [],
  exports: []
})
export class AppModule {}
