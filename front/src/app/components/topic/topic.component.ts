import {Component, inject, input, output} from "@angular/core";
import {TopicsService} from "../../core/services/topics.service";
import {UserSubscribedTopic} from "../../core/interfaces/userSubscribedTopic.interface";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  imports: [],
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {
  topic = input.required<UserSubscribedTopic>();
  topicService = inject(TopicsService);
  topicChanged = output();
  canUnsubscribe = input.required<boolean>()

  subscribeToTopic() {
    this.topicService.subscribe(this.topic().id).subscribe({
      next: () => {
        this.topicChanged.emit();
      }, error: (err) => {
        console.error('Error subscribing to topic', err);
      }
    });
  }


  unSubscribeToTopic() {
    this.topicService.unsubscribe(this.topic().id).subscribe({
      next: () => {
        this.topicChanged.emit();
      }, error: (err) => {
        console.error('Error subscribing to topic', err);
      }
    })
  }
}
