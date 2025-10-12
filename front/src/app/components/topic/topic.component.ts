import {Component, inject, input, output} from "@angular/core";
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardTitle
} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {TopicsService} from "../../core/services/topics.service";
import {UserSubscribedTopic} from "../../core/interfaces/userSubscribedTopic.interface";

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
  topic = input.required<UserSubscribedTopic>();
  topicService = inject(TopicsService);
  topicChanged = output();

  subscribeToTopic() {
    this.topicService.subscribe(this.topic().id).subscribe({
      next: () => {
        this.topicChanged.emit();
    }, error: (err) => {
      console.error('Error subscribing to topic', err);
    }
    });
  }



}
