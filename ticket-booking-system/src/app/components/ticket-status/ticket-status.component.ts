import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-ticket-status',
  templateUrl: './ticket-status.component.html',
  styleUrls: ['./ticket-status.component.css'],
  standalone: true,
})
export class TicketStatusComponent implements OnInit {
  currentTickets: number = 0;
  totalTicketsSold: number = 0;
  maxCapacity: number = 500;

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.refreshStatus();
    setInterval(() => this.refreshStatus(), 5000); // Poll every 5 seconds
  }

  refreshStatus(): void {
    this.ticketService.getCurrentTickets().then((data) => {
      this.currentTickets = data;
    });

    this.ticketService.getTotalTicketsSold().then((data) => {
      this.totalTicketsSold = data;
    });
  }
}
