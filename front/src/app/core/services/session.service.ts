import { Injectable, inject } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private readonly TOKEN_KEY = 'auth_token';

  public isLogged = false;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.isLogged = false;
    this.next();
  }

  public getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  public isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }

  constructor() {
    // Initialize isLogged based on token presence
    this.isLogged = this.isAuthenticated();
    if (this.isLogged) {
      this.next();
    }
  }
}
