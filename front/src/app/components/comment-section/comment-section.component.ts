import { AsyncPipe } from '@angular/common';
import { Component, inject, input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentResponse } from 'src/app/core/interfaces/commentResponse.interface';
import { CommentsService } from 'src/app/core/services/comments.service';
import { CommentComponent } from "../comment/comment.component";
import { CommentWriterComponent } from "../comment-writer/comment-writer.component";

@Component({
  selector: 'app-comment-section',
  imports: [AsyncPipe, CommentComponent, CommentWriterComponent],
  templateUrl: './comment-section.component.html',
  styleUrl: './comment-section.component.css'
})
export class CommentSectionComponent implements OnInit {
  postId = input.required<number>()

  commentsService = inject(CommentsService)
  comments$!: Observable<CommentResponse[]>;

  ngOnInit(): void {
    this.loadComments()
  }

  loadComments() {
    const id = this.postId();
    this.comments$ = this.commentsService.getPostComments(id);
  }

}
