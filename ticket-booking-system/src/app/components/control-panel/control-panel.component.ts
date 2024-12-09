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
        // Check if response contains message or status indicating success
        if (response && response.message) {
          console.log('System started:', response.message);
          alert('Spring Boot Application started successfully.');
        } else {
          // In case the response structure is unexpected
          console.error('Unexpected response format:', response);
          alert('Failed to start the Spring Boot Application.');
        }
      },
      (error) => {
        // Log the error and handle appropriately
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
        // Check if response contains message or status indicating success
        if (response && response.message) {
          console.log('System stopped:', response.message);
          alert('Spring Boot Application stopped successfully.');
        } else {
          // In case the response structure is unexpected
          console.error('Unexpected response format:', response);
          alert('Failed to stop the Spring Boot Application.');
        }
      },
      (error) => {
        // Log the error and handle appropriately
        console.error('Error stopping system:', error);
        if (error.status === 500) {
          alert('Server error: Could not stop the Spring Boot application.');
        } else {
          alert('Failed to stop the Spring Boot Application.');
        }
      }
    );
  }

  handleError(error: any, defaultMessage: string) {
    // Improved error handling to give more information on what failed
    if (error.status === 403) {
      console.error('Access is forbidden. Check your CORS or authentication settings.');
      alert('Access forbidden. Please check system permissions.');
    } else if (error.status === 404) {
      console.error('Endpoint not found. Check your URL.');
      alert('System action failed. Endpoint not found.');
    } else if (error.status === 500) {
      console.error('Server error. Please try again later.');
      alert('System action failed due to server error.');
    } else {
      console.error(`Error status: ${error.status}, Error message: ${error.message}`);
      alert(defaultMessage);
    }
  }
}
