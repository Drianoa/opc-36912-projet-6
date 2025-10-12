import {Component, signal, computed, ChangeDetectionStrategy, inject} from '@angular/core';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from "@angular/material/card";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButton } from "@angular/material/button";
import { MatFormField, MatInput } from "@angular/material/input";
import { MatIcon } from "@angular/material/icon";
import { AuthService } from "../../../core/services/auth.service";
import { Router } from "@angular/router";
import { LoginRequest } from "../../../core/interfaces/loginRequest.interface";
import { LoginResponse } from "../../../core/interfaces/loginResponse.interface";

/**
 * Login component for user authentication
 * Uses Angular signals for reactive state management and modern Angular Material 20+ patterns
 */
@Component({
  selector: 'app-login',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    ReactiveFormsModule,
    MatInput,
    MatButton
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent {
  authService = inject(AuthService)
  router = inject(Router)

  protected readonly hasError = signal(false);

  // Form using reactive forms with FormControl
  protected readonly form = new FormGroup({
    login: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  /**
   * Handles form submission
   */
  protected submit(): void {
    console.log("Submitting form", this.form.value);
    if (this.form.invalid) {
      return;
    }

    const loginRequest = this.form.value as LoginRequest;

    this.authService.login(loginRequest).subscribe({
      next: (_: LoginResponse) => {
        this.hasError.set(false);
        this.router.navigate(['/']);
      },
      error: (_) => {
        this.hasError.set(true);
      }
    });
  }
}
