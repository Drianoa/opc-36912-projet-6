import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostRequest } from '../interfaces/postRequest.interface';
import { PostResponse } from '../interfaces/postResponse.interface';
import { MessageResponse } from '../interfaces/messageResponse.interface';

export type SortDirection = 'NEWEST' | 'OLDEST';

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  private http = inject(HttpClient);
  private apiUrl = 'api/posts';

  public getPosts(sort?: SortDirection): Observable<PostResponse[]> {
    let params = new HttpParams();
    if (sort) {
      params = params.set('sort', sort);
    }
    return this.http.get<PostResponse[]>(this.apiUrl, { params });
  }

  public createPost(postRequest: PostRequest): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(this.apiUrl, postRequest);
  }

  public getPostById(id: number): Observable<PostResponse> {
    return this.http.get<PostResponse>(`${this.apiUrl}/${id}`);
  }
}
