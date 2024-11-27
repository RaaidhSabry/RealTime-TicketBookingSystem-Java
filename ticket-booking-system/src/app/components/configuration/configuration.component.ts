import { Component } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class ConfigurationComponent {
  maxCapacity: number = 500;
  message: string = '';

  constructor(private ticketService: TicketService) {}

  submitConfig(): void {
    this.ticketService.updateMaxCapacity(this.maxCapacity).then((response) => {
      this.message = response;
    });
  }
}
