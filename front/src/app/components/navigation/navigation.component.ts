import {Component, inject, OnInit, signal} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {SessionService} from "../../core/services/session.service";
import {PostsService} from "../../core/services/posts.service";
import {BackButtonComponent} from "../back-button/back-button.component";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    BackButtonComponent
  ]
})
export class NavigationComponent implements OnInit {
  sessionService = inject(SessionService)
  router = inject(Router)
  postsService = inject(PostsService)
  isOpen = signal(false)


  ngOnInit(): void {
    this.postsService.getPosts().subscribe({
      next: (posts) => {
        if (posts.length > 0) {
          this.router.navigate(['posts']);
        } else {
          this.router.navigate(['topics']);
        }
      }, error: (err) => {
        console.error('Error fetching posts on navigation init:', err);
      }
    });
  }

  logout() {
    this.sessionService.logOut();
    this.router.navigate(['']);
  };

  showBackButton(): boolean {
    const currentUrl = this.router.url;
    return currentUrl !== '/' && currentUrl !== '/posts' && currentUrl !== '/topics' && currentUrl !== '/me';
  }

  open() {
    this.isOpen.set(true);
  }

  close() {
    this.isOpen.set(false);
  }
}
