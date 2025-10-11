import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Topic} from "../models/Topic";

@Injectable({
  providedIn: 'root'
})
export class TopicsService {
  private http = inject(HttpClient);


  findAll() {
    return this.http.get<Topic[]>('/api/topic')
  }


}
