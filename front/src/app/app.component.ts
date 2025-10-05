import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavigationComponent} from "./core/composants/navigation/navigation.component";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
  imports: [RouterOutlet, NavigationComponent]
})
export class AppComponent {
  title = 'mdd-client';
}
