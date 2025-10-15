import {Component, inject} from '@angular/core';
import {FormGroup, Validators, ReactiveFormsModule, FormControl} from '@angular/forms';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {AuthService} from '../../../core/services/auth.service';
import {RegisterRequest} from "../../../core/interfaces/registerRequest.interface";

@Component({
  selector: 'app-register',
  imports: [
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  isLoading = false;
  errorMessage = '';

  router = inject(Router)
  authService = inject(AuthService)

  protected readonly registerForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required,
      Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z//d]).{8,}$')])
  });


  onSubmit(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';

      const registerData: RegisterRequest = this.registerForm.value as RegisterRequest;

      this.authService.register(registerData).subscribe({
        next: () => {
          this.isLoading = false;
          // Redirect to home page or login page after successful registration
          this.router.navigate(['/']);
        },
        error: (error) => {
          this.isLoading = false;

          if (error.status === 400) {
            this.errorMessage = 'Email already exists or invalid data provided';
          } else if (error.status === 0) {
            this.errorMessage = 'Unable to connect to server. Please check if the backend is running.';
          } else {
            this.errorMessage = 'Registration failed. Please try again.';
          }
        }
      });
    } else {
      this.markFormGroupTouched();
    }
  }

  private markFormGroupTouched(): void {
    Object.keys(this.registerForm.controls).forEach(key => {
      const control = this.registerForm.get(key);
      control?.markAsTouched();
    });
  }

}
