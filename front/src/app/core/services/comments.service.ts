import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentRequest } from '../interfaces/commentRequest.interface';
import { CommentResponse } from '../interfaces/commentResponse.interface';
import { MessageResponse } from '../interfaces/messageResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  private http = inject(HttpClient);
  private apiUrl = 'api/comments';

  public getPostComments(postId: number): Observable<CommentResponse[]> {
    return this.http.get<CommentResponse[]>(`${this.apiUrl}/${postId}`);
  }

  public addComment(postId: number, commentRequest: CommentRequest): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.apiUrl}/${postId}`, commentRequest);
  }
}
