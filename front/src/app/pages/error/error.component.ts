import { Component } from '@angular/core';
import { BackButtonComponent } from "src/app/components/back-button/back-button.component";

@Component({
  selector: 'app-error',
  imports: [BackButtonComponent],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {

}
