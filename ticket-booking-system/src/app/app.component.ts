import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { TicketService } from './services/ticket.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
import { CommonModule } from '@angular/common';
import {CustomerComponent} from './components/customer/customer.component';
import {AuthService} from './services/auth.service';
import {TicketStatusComponent} from './components/ticket-status/ticket-status.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [
    ControlPanelComponent,
    CommonModule,
    CustomerComponent,
    ConfigurationFormComponent,
    TicketStatusComponent,
  ],
})
export class AppComponent{

  customerName: string = '';
  title = 'ticket-booking-system';

  role: string | null = null;

  constructor(private authService: AuthService, private dialog: MatDialog, private ticketService: TicketService, private router: Router, private http: HttpClient) {}


  openLoginDialog() {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.role = result.role;
      }
    });
  }

  openSignupDialog() {
    this.dialog.open(SignupComponent, {
      width: '400px',
    });
  }

  // Simulate logout
  onLogout() {
    this.role = null;
    this.router.navigate(['/']);
  }
}
