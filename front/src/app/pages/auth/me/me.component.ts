import {Component, inject, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../core/services/auth.service";
import {Router} from "@angular/router";
import {RegisterRequest} from "../../../core/interfaces/registerRequest.interface";
import {SessionService} from "../../../core/services/session.service";
import {TopicsService} from "../../../core/services/topics.service";
import {TopicComponent} from "../../../components/topic/topic.component";
import {AsyncPipe} from "@angular/common";

@Component({
  selector: 'app-me',
  imports: [
    ReactiveFormsModule,
    TopicComponent,
    AsyncPipe
  ],
  templateUrl: './me.component.html',
  styleUrl: './me.component.css'
})
export class MeComponent {
  authService = inject(AuthService)
  sessionService = inject(SessionService)
  topicsService = inject(TopicsService)
  router = inject(Router)

  topics$ = this.topicsService.getSubscribedTopics();

  protected readonly hasError = signal(false);

  // Form using reactive forms with FormControl
  protected readonly form = new FormGroup({
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
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

    const registerRequest = this.form.value as RegisterRequest;
    const userId = this.sessionService.getUserId();
    if (!userId) {
      this.hasError.set(true);
      return;
    }

    this.authService.updateUser(registerRequest, userId).subscribe({
      next: () => {
        this.hasError.set(false);
        this.router.navigate(['/']);
      },
      error: () => {
        this.hasError.set(true);
      }
    });
  }

  reloadTopics() {
    this.topics$ = this.topicsService.getSubscribedTopics();
  }
}
