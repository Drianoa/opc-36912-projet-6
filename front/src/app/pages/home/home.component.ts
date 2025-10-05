import { Component, OnInit } from '@angular/core';
import { MatButton } from '@angular/material/button';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
    imports: [MatButton]
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  subscribe() {
    alert('Inscrivez-vous pour accéder aux fonctionnalités avancées !');
  }

  login() {
    alert('Connectez-vous pour continuer !');
  }
}
