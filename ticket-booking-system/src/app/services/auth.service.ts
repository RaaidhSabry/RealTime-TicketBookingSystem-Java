import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  signup(user: { name: string; email: string; password: string; role: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/signup`, user, { responseType: 'text' as 'json'});
  }


  login(user: { email: string; password: string; role: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, user, { responseType: 'text' as 'json'});
  }

  getUserByEmail(email: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/?email=${email}`);
  }
}
