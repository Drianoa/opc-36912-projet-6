import {Component, inject} from '@angular/core';
import {TopicsService} from "../../../core/services/topics.service";
import {AsyncPipe} from "@angular/common";
import {TopicComponent} from "../../../components/topic/topic.component";

@Component({
  selector: 'app-topics',
  imports: [
    AsyncPipe,
    TopicComponent
  ],
  templateUrl: './topics.component.html',
  styleUrl: './topics.component.scss'
})
export class TopicsComponent {

  topicsService = inject(TopicsService);
  topics$ = this.topicsService.getAllTopics();

  reloadTopics() {
    this.topics$ = this.topicsService.getAllTopics();
  }
}
