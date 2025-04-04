import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
  imports: [FormsModule, NgIf],
  standalone: true
})
export class CustomerComponent {
  @Input() customerName: string = '';
  @Input() isHeader: boolean = false;
  ticketMessage: string = '';
  phone: string = '';
  numberOfTickets: number = 1;
  paymentMethod: string = 'creditCard';
  remainingTickets: number = 50;
  ticketsSold: number = 0;

  constructor(private http: HttpClient, private ticketService: TicketService) {}

  fetchTicketData() {
    this.http.get<{ remainingTickets: number; ticketsSold: number }>('http://localhost:8080/api/ticket/customer/data')
      .subscribe({
        next: (data) => {
          this.remainingTickets = data.remainingTickets;
          this.ticketsSold = data.ticketsSold;
        },
        error: (error) => {
          console.error('Failed to fetch ticket data:', error);
        }
      });
  }

  validateInputs(): boolean {
    // Check if all fields are filled
    if (!this.phone || !this.numberOfTickets || !this.paymentMethod) {
      alert('Please fill in all fields before purchasing a ticket.');
      return false;
    }

    // Validate phone number format (10 digits starting with 0)
    const phonePattern = /^0\d{9}$/;
    if (!phonePattern.test(this.phone)) {
      alert('Invalid phone number. It must be 10 digits and start with 0.');
      return false;
    }

    return true;
  }

  buyTicket() {
    if (!this.validateInputs()) {
      return;
    }

    const requestPayload = {
      customerName: this.customerName,
      phone: this.phone,
      numberOfTickets: this.numberOfTickets,
      paymentMethod: this.paymentMethod,
    };

    this.ticketService.buyTickets(requestPayload).subscribe(
      (response) => {
        this.ticketMessage = response.message;
        this.fetchTicketData();
      },
      (error) => {
        console.error('Ticket purchase error:', error);

        // Extract a meaningful error message
        const errorMessage = error.error?.message || (error.message ? error.message : 'Unexpected error occurred. Please contact support.');

        alert(errorMessage);
        this.ticketMessage = errorMessage;
      }
    );
  }
}
