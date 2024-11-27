import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ticket-pool',
  templateUrl: './ticket-pool.component.html',
  styleUrls: ['./ticket-pool.component.css'],
  imports: [FormsModule],
  standalone: true,
})
export class TicketPoolComponent implements OnInit {
  currentTickets: number = 0;
  totalTicketsSold: number = 0;
  ticketsToAdd: number = 0;
  ticketsToRemove: number = 0;
  message: string = '';

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.fetchTicketData();
  }

  fetchTicketData(): void {
    this.ticketService
      .getCurrentTickets()
      .then((data) => {
        this.currentTickets = data;
      })
      .catch(() => {
        this.message = 'Error fetching current tickets.';
      });

    this.ticketService
      .getTotalTicketsSold()
      .then((data) => {
        this.totalTicketsSold = data;
      })
      .catch(() => {
        this.message = 'Error fetching total tickets sold.';
      });
  }

  addTickets(): void {
    if (this.ticketsToAdd <= 0) {
      this.message = 'Please enter a valid number of tickets to add.';
      return;
    }

    this.ticketService
      .addTickets(this.ticketsToAdd)
      .then((response) => {
        this.message = response;
        this.fetchTicketData();
        this.ticketsToAdd = 0;
      })
      .catch(() => {
        this.message = 'Error adding tickets.';
      });
  }

  removeTickets(): void {
    if (this.ticketsToRemove <= 0) {
      this.message = 'Please enter a valid number of tickets to remove.';
      return;
    }

    this.ticketService
      .removeTickets(this.ticketsToRemove)
      .then((response) => {
        this.message = response;
        this.fetchTicketData();
        this.ticketsToRemove = 0;
      })
      .catch(() => {
        this.message = 'Error removing tickets.';
      });
  }
}
