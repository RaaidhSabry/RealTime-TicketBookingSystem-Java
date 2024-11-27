import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../services/ticket.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-log-display',
  templateUrl: './log-display.component.html',
  styleUrls: ['./log-display.component.css'],
  standalone: true,
  imports: [CommonModule],
})
export class LogDisplayComponent implements OnInit {
  logs: string[] = [];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.refreshLogs();
    setInterval(() => this.refreshLogs(), 5000); // Poll logs every 5 seconds
  }

  refreshLogs(): void {
    this.ticketService.getLogs().then((logs) => {
      this.logs = logs;
    });
  }
}
