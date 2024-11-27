import { Component } from '@angular/core';
import { TicketService } from '../../services/ticket.service';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css'],
  standalone: true,
})
export class ControlPanelComponent {
  message: string = '';

  constructor(private ticketService: TicketService) {}

  addTickets(quantity: number): void {
    this.ticketService.addTickets(quantity).then((response) => {
      this.message = response;
    });
  }

  removeTickets(quantity: number): void {
    this.ticketService.removeTickets(quantity).then((response) => {
      this.message = response;
    });
  }
}
