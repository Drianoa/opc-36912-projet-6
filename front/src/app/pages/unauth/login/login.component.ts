import {Component, signal, ChangeDetectionStrategy, inject} from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatInput} from "@angular/material/input";
import {AuthService} from "../../../core/services/auth.service";
import {Router} from "@angular/router";
import {LoginRequest} from "../../../core/interfaces/loginRequest.interface";

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
  styleUrl: './login.component.css',
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
      next: () => {
        this.form.reset();
        this.hasError.set(false);
        this.router.navigate(['/']);
      },
      error: () => {
        this.hasError.set(true);
      }
    });
  }
}
