import {Component, inject, signal} from '@angular/core';
import {TopicsService} from "../../../core/services/topics.service";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {PostRequest} from "../../../core/interfaces/postRequest.interface";
import {PostsService} from "../../../core/services/posts.service";
import {AsyncPipe} from "@angular/common";
import {Router} from "@angular/router";
import {BackButtonComponent} from "src/app/components/back-button/back-button.component";

@Component({
  selector: 'app-new-post',
  imports: [
    ReactiveFormsModule,
    AsyncPipe,
    BackButtonComponent
  ],
  templateUrl: './new-post.component.html',
  styleUrl: './new-post.component.css'
})
export class NewPostComponent {
  topicsService = inject(TopicsService)
  postsService = inject(PostsService)
  router = inject(Router)
  hasError = signal(false);

  topics$ = this.topicsService.getAllTopics();

  protected readonly form = new FormGroup({
    title: new FormControl('', [Validators.required]),
    content: new FormControl('', [Validators.required, Validators.maxLength(2000)]),
    topicId: new FormControl('', [Validators.required])
  });

  submit(): void {
    if (this.form.invalid) {
      return;
    }

    const formValue = this.form.value as {
      title: string;
      content: string;
      topicId: string;
    };

    const postRequest: PostRequest = {
      title: formValue.title!,
      content: formValue.content!,
      topicId: Number(formValue.topicId!) // Conversion explicite
    };

    this.postsService.createPost(postRequest).subscribe({
      next: () => {
        this.hasError.set(false);
        this.router.navigate(['/']);
      },
      error: () => {
        this.hasError.set(true);
      }
    })
  }

}
