import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
  imports: [
    FormsModule,
    NgIf
  ],
  standalone: true
})
export class ConfigurationFormComponent {
  maxTicketCapacity: number = 5000; // Default maximum ticket capacity
  totalTickets: number = 0; // Default total tickets
  ticketReleaseRate: number = 1; // Default ticket release rate in minutes
  customerRetrievalRate: number = 1; // Default customer retrieval rate in seconds
  configurationMessage: string = ''; // Message to display configuration updates
  systemStatus: string = 'running'; // Default system status

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.updateConfiguration(); // Fetch configuration data when the component initializes
  }

  updateConfiguration() {
    // Validate input fields before making a request
    if (!this.validateInputs()) {
      return;
    }

    const requestPayload = {
      maxTicketCapacity: this.maxTicketCapacity,
      totalTickets: this.totalTickets,
      ticketReleaseRate: this.ticketReleaseRate,
      customerRetrievalRate: this.customerRetrievalRate,
    };

    this.http.post<{ message: string }>('http://localhost:8080/api/ticket/configuration', requestPayload)
      .subscribe({
        next: (response) => {
          this.configurationMessage = response.message; // Display success message
          alert('Configuration updated successfully.');
        },
        error: (error) => {
          this.handleError(error, 'Configuration update failed. Please try again later.');
        }
      });
  }

  // Validation logic
  validateInputs(): boolean {
    if (!this.maxTicketCapacity || !this.totalTickets || !this.ticketReleaseRate || !this.customerRetrievalRate) {
      return false;
    }

    if (this.maxTicketCapacity < 1 || this.maxTicketCapacity > 5000) {
      alert('Maximum ticket capacity must be between 1 and 5000.');
      return false;
    }

    if (this.totalTickets < 1 || this.totalTickets > this.maxTicketCapacity) {
      alert('Total tickets must be between 1 and the maximum ticket capacity.');
      return false;
    }

    if (this.ticketReleaseRate < 1 || this.ticketReleaseRate > 5) {
      alert('Ticket release rate must be between 1 and 5 minutes.');
      return false;
    }

    if (this.customerRetrievalRate < 1 || this.customerRetrievalRate > 10) {
      alert('Customer retrieval rate must be between 1 and 10 seconds.');
      return false;
    }

    return true;
  }

  // Improved error handling method
  handleError(error: any, defaultMessage: string) {
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
