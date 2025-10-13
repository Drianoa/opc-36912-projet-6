import {Component, inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {Router} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-back-button',
  imports: [
    MatButton,
    MatIcon
  ],
  templateUrl: './back-button.component.html',
  styleUrl: './back-button.component.scss'
})
export class BackButtonComponent {
  router = inject(Router)

  back() {
    this.router.navigate(['/']);
  }
}
