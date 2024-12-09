import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service'; // Adjust the path accordingly
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-vendor',
  templateUrl: './vendor.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./vendor.component.css']
})
export class VendorComponent implements OnInit {
  ticketsToAdd: number = 0;
  releaseRate: number = 5; // Set a default release rate
  statusMessage: string = '';

  constructor(private ticketService: TicketService, private router: Router) {}

  ngOnInit() {
    this.loadTicketInfo();
  }

  loadTicketInfo() {
    // Fetch ticket info or configuration if needed
  }

  addTickets() {
    if (this.ticketsToAdd > 0) {
      this.ticketService.addTickets(this.ticketsToAdd).subscribe(response => {
        this.statusMessage = `${this.ticketsToAdd} tickets added to the pool.`;
        this.ticketsToAdd = 0; // Reset input field
      }, error => {
        this.statusMessage = 'Error adding tickets. Please try again later.';
        console.error('Error adding tickets', error);
      });
    } else {
      this.statusMessage = 'Please enter a valid number of tickets.';
    }
  }

  updateReleaseRate() {
    console.log("Release rate updated.");
  }

}
