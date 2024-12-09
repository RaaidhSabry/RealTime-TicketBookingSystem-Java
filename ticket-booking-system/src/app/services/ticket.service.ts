import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/api/ticket';

  constructor(private http: HttpClient) {}

  // Add tickets to the pool
  addTickets(numberOfTickets: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/add`, { numberOfTickets });
  }

  // Remove tickets from the pool
  removeTickets(numberOfTickets: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/remove`, { numberOfTickets });
  }

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

  // Update configuration (use new endpoint `/update` from the backend)
  updateConfiguration(configuration: {
    maxTicketCapacity: number;
    totalTickets: number;
    ticketReleaseRate: number;
    customerRetrievalRate: number;
  }): Observable<any> {
    return this.http.post(`${this.apiUrl}/configuration`, configuration);
  }


  getTicketStatus(): Observable<any> {
    return this.http.get(`${this.apiUrl}/status`);
  }

  startTicketOperations(): Observable<any> {
    return this.http.post(`${this.apiUrl}/start`, {});
  }

  stopTicketOperations(): Observable<any> {
    return this.http.post(`${this.apiUrl}/stop`, {});
  }

  resetTicketOperations(): Observable<any> {
    return this.http.post(`${this.apiUrl}/reset`, {});
  }

  saveConfiguration(config: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/saveConfig`, config);
  }

  startSystem(): Observable<{ message: string }> {
    return this.http.post<{ message: string }>('http://localhost:8080/api/system/start', {});
  }

  stopSystem(): Observable<{ message: string }> {
    return this.http.post<{ message: string }>('http://localhost:8080/api/system/stop', {});
  }

  getSystemStatus(): Observable<any> {
    return this.http.post(`http://localhost:8080/api/system/status`, {});
  }

}
