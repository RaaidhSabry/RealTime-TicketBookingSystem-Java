import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/api/ticket';

  constructor(private http: HttpClient) {}

  // Get ticket info
  getTicketInfo(): Observable<{ remainingTickets: number }> {
    return this.http.get<{ remainingTickets: number }>(`${this.apiUrl}/info`);
  }

  // Buy tickets
  buyTickets(requestPayload: {
    customerName: string;
    phone: string;
    numberOfTickets: number;
    paymentMethod: string;
  }): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.apiUrl}/customer/buy`, requestPayload);
  }

  startSystem(): Observable<{ message: string }> {
    return this.http.post<{ message: string }>('http://localhost:8080/api/system/start', {});
  }

  stopSystem(): Observable<{ message: string }> {
    return this.http.post<{ message: string }>('http://localhost:8080/api/system/stop', {});
  }

}
