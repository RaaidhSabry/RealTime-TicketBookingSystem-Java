import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private apiUrl = ''; // Backend URL
  getCurrentTickets(): Promise<number> {
    return axios.get(`${this.apiUrl}/currentTickets`).then((res) => res.data);

  }

  addTickets(quantity: number): Promise<string> {
    return axios
      .post(`${this.apiUrl}/addTickets`, { quantity })
      .then((res) => res.data);
  }

  removeTickets(quantity: number): Promise<string> {
    return axios
      .post(`${this.apiUrl}/removeTickets`, { quantity })
      .then((res) => res.data);
  }

  getLogs(): Promise<string[]> {
    return axios.get(`${this.apiUrl}/logs`).then((res) => res.data);
  }

  updateMaxCapacity(maxCapacity: number): Promise<string> {
    return axios
      .post(`${this.apiUrl}/updateMaxCapacity`, { capacity: maxCapacity })
      .then((res) => res.data);
  }

  getTotalTicketsSold(): Promise<number> {
    return axios
      .get(`${this.apiUrl}/totalTicketsSold`)
      .then((res) => res.data);
  }
}
