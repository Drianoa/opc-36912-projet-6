import { CommentResponse } from './../../core/interfaces/commentResponse.interface';
import { Component, input } from '@angular/core';
import {} from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css'],
})
export class CommentComponent {
  comment = input.required<CommentResponse>();
}
