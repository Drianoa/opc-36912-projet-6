import { output } from '@angular/core';
import { CommentsService } from 'src/app/core/services/comments.service';
import { CommentRequest } from './../../core/interfaces/commentRequest.interface';
import { Component, inject, input } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-comment-writer',
  imports: [ReactiveFormsModule],
  templateUrl: './comment-writer.component.html',
  styleUrl: './comment-writer.component.css',
})
export class CommentWriterComponent {
  postId = input.required<number>();
  newComment = output();
  commentsService = inject(CommentsService);

  message = new FormGroup({
    message: new FormControl('', Validators.required)
  });

  submit() {
    if(this.message.invalid) return

    const messageValue = this.message.value.message!

    const commentRequest : CommentRequest = {
      message: messageValue,
    }

    this.commentsService.addComment(this.postId(), commentRequest).subscribe({
      next: () => {
        this.message.reset();
        this.newComment.emit();
      }
    })
  }
}
