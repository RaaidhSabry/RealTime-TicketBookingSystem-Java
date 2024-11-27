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
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { ConfigurationComponent } from './components/configuration/configuration.component';
import { TicketStatusComponent } from './components/ticket-status/ticket-status.component';
import { LogDisplayComponent } from './components/log-display/log-display.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {SignupComponent} from './components/signup/signup.component';
import {LoginComponent} from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    TicketPoolComponent,  // Declare all your components here
    VendorComponent,
    TicketStatusComponent,
    ControlPanelComponent,
    ConfigurationComponent,
    LogDisplayComponent
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
  exports: [
    ControlPanelComponent,
    ConfigurationComponent,
    LogDisplayComponent,
    TicketStatusComponent
  ]
})
export class AppModule {}
