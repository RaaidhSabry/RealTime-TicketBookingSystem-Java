import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TicketStatusComponent } from './components/ticket-status/ticket-status.component';  // Import your standalone component
import { ControlPanelComponent } from './components/control-panel/control-panel.component';  // Import your standalone component
import { ConfigurationComponent } from './components/configuration/configuration.component';  // Import your standalone component
import { LogDisplayComponent } from './components/log-display/log-display.component';  // Import your standalone component
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { RouterModule } from '@angular/router';  // Import RouterModule for routing
import { MatButtonModule } from '@angular/material/button';
import { TicketService } from './services/ticket.service';  // Import necessary Material components

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    TicketStatusComponent,  // Include your standalone component here
    ControlPanelComponent,  // Include your standalone component here
    ConfigurationComponent,  // Include your standalone component here
    LogDisplayComponent,     // Include your standalone component here
    RouterModule,            // Add RouterModule for routing
    MatButtonModule,         // Add button module if used in your template
  ]
})
export class AppComponent {
  title = 'ticket-booking-system';

  // Injecting services and dialog into the constructor
  constructor(
    private dialog: MatDialog,       // MatDialog for opening login/signup dialogs
    private ticketService: TicketService  // TicketService for backend communication
  ) {}

  // Open Login Dialog
  openLoginDialog() {
    this.dialog.open(LoginComponent, {
      width: '400px',  // Setting the width of the dialog
    });
  }

  // Open Signup Dialog
  openSignupDialog() {
    this.dialog.open(SignupComponent, {
      width: '400px',  // Setting the width of the dialog
    });
  }

  ngOnInit() {
    this.ticketService.getCurrentTickets().then((data) => {
      console.log(data);
    });
  }

}
