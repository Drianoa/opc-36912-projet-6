import { Component, inject, OnDestroy, OnInit, signal } from '@angular/core';
import {
  NavigationEnd,
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { SessionService } from '../../core/services/session.service';
import { PostsService } from '../../core/services/posts.service';
import { filter, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
})
export class NavigationComponent {
  sessionService = inject(SessionService);
  router = inject(Router);
  postsService = inject(PostsService);
  isOpen = signal(false);

  logout() {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }

  open() {
    this.isOpen.set(true);
  }

  close() {
    this.isOpen.set(false);
  }
}
