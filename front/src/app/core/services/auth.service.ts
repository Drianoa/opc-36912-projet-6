import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { LoginResponse } from '../interfaces/loginResponse.interface';
import { UserResponse } from '../interfaces/userResponse.interface';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private sessionService = inject(SessionService);
  private apiUrl = 'api/auth';

  public register(registerRequest: RegisterRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/register`, registerRequest)
      .pipe(
        tap(response => {
          if (response.token) {
            this.sessionService.logIn(response.token);
          }
        })
      );
  }

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest)
      .pipe(
        tap(response => {
          if (response.token) {
            this.sessionService.logIn(response.token);
          }
        })
      );
  }

  public me(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.apiUrl}/me`);
  }

  public logout(): void {
    this.sessionService.logOut();
  }
}
