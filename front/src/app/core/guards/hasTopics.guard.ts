import { map } from 'rxjs/operators';
import {CanActivateFn} from '@angular/router';
import {inject} from '@angular/core';
import { TopicsService } from '../services/topics.service';

export const hasTopicsGuard: CanActivateFn = () => {
  const topicsService = inject(TopicsService);
  return topicsService.getSubscribedTopics().pipe(
    map(topics => topics.length > 0)
  );
};
