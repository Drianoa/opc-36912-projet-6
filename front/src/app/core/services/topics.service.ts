import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserSubscribedTopic } from '../interfaces/userSubscribedTopic.interface';
import { UserTopic } from '../interfaces/userTopic.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicsService {
  private http = inject(HttpClient);
  private apiUrl = 'api/topics';

  public getAllTopics(): Observable<UserSubscribedTopic[]> {
    return this.http.get<UserSubscribedTopic[]>(`${this.apiUrl}`);
  }

  public getSubscribedTopics(): Observable<UserTopic[]> {
    return this.http.get<UserTopic[]>(`${this.apiUrl}/subscribed`);
  }
}
