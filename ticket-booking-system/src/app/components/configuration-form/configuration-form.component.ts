import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
  imports: [FormsModule, NgIf],
  standalone: true
})
export class ConfigurationFormComponent {
  maxTicketCapacity: number = 5000;
  totalTickets: number = 0;
  ticketReleaseRate: number = 1;
  customerRetrievalRate: number = 1;
  configurationMessage: string = '';

  constructor(private http: HttpClient) {}

  updateConfiguration() {
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
          this.configurationMessage = response.message;
          alert('Configuration updated successfully.');
        },
        error: (error) => {
          console.error('Configuration update failed:', error);
          alert('Configuration update failed. Please try again later.');
        }
      });
  }

  // Validation logic
  validateInputs(): boolean {
    if (!this.maxTicketCapacity || !this.totalTickets || !this.ticketReleaseRate || !this.customerRetrievalRate) {
      alert("Enter all fields !")
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
}
