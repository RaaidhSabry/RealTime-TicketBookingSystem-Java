import { Component, OnInit} from '@angular/core';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-ticket-status',
  templateUrl: './ticket-status.component.html',
  standalone: true,
  styleUrls: ['./ticket-status.component.css']
})
export class TicketStatusComponent implements OnInit {
  remainingTickets: number = 0; // Initialize with a default value to avoid undefined access
  systemStatus: string = 'stopped';

  constructor(private ticketService: TicketService) {}

  ngOnInit() {
    this.fetchRemainingTickets();
  }




  fetchRemainingTickets() {
    this.ticketService.getTicketInfo().subscribe(
      (data) => {
        // Assuming data is the response from the backend, and it contains a property 'remainingTickets'
        if (data && data.remainingTickets !== undefined) {
          this.remainingTickets = data.remainingTickets; // Update remaining tickets
          console.log('Remaining Tickets:', this.remainingTickets);
        } else {
          console.error('Unexpected response structure:', data);
        }
      },
      (error) => {
        // Improved error handling to give more information on what failed
        console.error('Error fetching ticket info:', error);
        if (error.status === 403) {
          console.error('Access is forbidden. Check your CORS or authentication settings.');
        } else if (error.status === 404) {
          console.error('Endpoint not found. Check your URL.');
        } else if (error.status === 500) {
          console.error('Server error. Please try again later.');
        } else {
          console.error(`Error status: ${error.status}, Error message: ${error.message}`);
        }
      }
    );
  }
}
