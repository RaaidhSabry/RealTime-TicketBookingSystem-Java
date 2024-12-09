import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-ticket-pool',
  templateUrl: './ticket-pool.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./ticket-pool.component.css']
})
export class TicketPoolComponent implements OnInit {

  currentTickets: number = 0;
  maxCapacity: number = 5000;
  ticketsToAdd: number = 0;
  ticketsToRemove: number = 0;
  statusMessage: string = '';

  constructor(private ticketService: TicketService) { }

  ngOnInit(): void {
  }


  // Add tickets to the pool
  addTickets(): void {
    if (this.ticketsToAdd > 0) {
      this.ticketService.addTickets(this.ticketsToAdd).subscribe(response => {
        this.statusMessage = response;
      });
    } else {
      this.statusMessage = 'Please enter a valid number of tickets to add.';
    }
  }

  // Remove tickets from the pool
  removeTickets(): void {
    if (this.ticketsToRemove > 0) {
      this.ticketService.removeTickets(this.ticketsToRemove).subscribe(response => {
        this.statusMessage = response;
      });
    } else {
      this.statusMessage = 'Please enter a valid number of tickets to remove.';
    }
  }
}
