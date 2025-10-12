import {Component, inject, input} from "@angular/core";
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardTitle
} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {UserTopic} from "../core/interfaces/userTopic.interface";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatButton,
    MatCardActions
  ],
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent {
  topic = input<UserTopic>();

  subscribeToTopic() {

  }



}
