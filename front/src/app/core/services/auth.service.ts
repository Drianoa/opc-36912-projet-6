import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated(): boolean {
    console.log('AuthService isAuthenticated');
    // Implement your authentication logic here
    return false; // or false based on the authentication status
  }
}
