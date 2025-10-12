import { Component } from '@angular/core';
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatFormField, MatInput, MatSuffix} from "@angular/material/input";
import {AuthService} from "../../core/services/auth.service";
import {Router} from "@angular/router";
import {SessionInformation} from "../../core/interfaces/sessionInformation.interface";
import {LoginRequest} from "../../core/interfaces/loginRequest.interface";
import {SessionService} from "../../core/services/session.service";

@Component({
  selector: 'app-login',
  imports: [
    MatCardHeader,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    FormsModule,
    MatIconButton,
    MatInput,
    MatSuffix,
    MatButton
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.min(3)
      ]
    ]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService) {
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/sessions']);
      },
      error: error => this.onError = true,
    });
  }
}
