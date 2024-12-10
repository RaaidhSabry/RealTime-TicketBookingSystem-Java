import { Component } from '@angular/core';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  standalone: true,
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {

  constructor(private ticketService: TicketService) {}

  startSystem() {
    this.ticketService.startSystem().subscribe(
      (response: any) => {
        if (response && response.message) {
          console.log('System started:', response.message);
          alert('Spring Boot Application started successfully.');
        } else {
          console.error('Unexpected response format:', response);
          alert('Failed to start the Spring Boot Application.');
        }
      },
      (error) => {
        console.error('Error starting system:', error);
        if (error.status === 500) {
          alert('Server error: Could not start the Spring Boot application.');
        } else {
          alert('Failed to start the Spring Boot Application.');
        }
      }
    );
  }

  stopSystem() {
    this.ticketService.stopSystem().subscribe(
      (response: any) => {
        if (response && response.message) {
          console.log('System stopped:', response.message);
          alert('Spring Boot Application stopped successfully.');
        } else {
          console.error('Unexpected response format:', response);
          alert('Failed to stop the Spring Boot Application.');
        }
      },
      (error) => {
        console.error('Error stopping system:', error);
        if (error.status === 500) {
          alert('Server error: Could not stop the Spring Boot application.');
        } else {
          alert('Failed to stop the Spring Boot Application.');
        }
      }
    );
  }
}
