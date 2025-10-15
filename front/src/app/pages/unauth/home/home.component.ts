import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [NgOptimizedImage]
})
export class HomeComponent {
  router = inject(Router);

  register() {
    this.router.navigate(['/register']);
  }

  login() {
    this.router.navigate(['/login']);
  }
}
