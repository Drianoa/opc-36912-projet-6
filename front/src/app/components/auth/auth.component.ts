import {Component} from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {BackButtonComponent} from "../back-button/back-button.component";

@Component({
  selector: 'app-auth',
  imports: [
    RouterOutlet,
    BackButtonComponent,
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {

}
