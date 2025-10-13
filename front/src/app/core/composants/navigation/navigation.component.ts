import {Component, inject, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {AsyncPipe} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {SessionService} from "../../services/session.service";
import {PostsService} from "../../services/posts.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss',
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    AsyncPipe,
    RouterOutlet,
    RouterLink,
    RouterLinkActive
  ]
})
export class NavigationComponent implements OnInit {
  private breakpointObserver = inject(BreakpointObserver);
  sessionService = inject(SessionService)
  router = inject(Router)
  postsService = inject(PostsService)

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.HandsetPortrait)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );


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
    })
  }

  logout() {
    this.sessionService.logOut();
    this.router.navigate(['']);
  };
}
