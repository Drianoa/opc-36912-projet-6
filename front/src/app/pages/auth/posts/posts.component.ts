import {Component, inject, signal} from '@angular/core';
import {PostsService, SortDirection} from "../../../core/services/posts.service";
import {PostPreviewComponent} from "../../../components/post-preview/post-preview.component";
import {AsyncPipe} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-posts',
  imports: [
    PostPreviewComponent,
    AsyncPipe,
    MatButton,
    RouterLink,
    MatIcon,
  ],
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.css'
})
export class PostsComponent {
  postService = inject(PostsService);
  sort = signal<SortDirection>('NEWEST')
  posts$ = this.postService.getPosts(this.sort());

  toogleSort() {
    this.sort.set(this.sort() === 'NEWEST' ? 'OLDEST' : 'NEWEST')
    this.posts$ = this.postService.getPosts(this.sort());
  }

}
