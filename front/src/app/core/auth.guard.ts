import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { SessionService } from './services/session.service';

export const authGuard: CanActivateFn = (route, state) => {
  const sessionService = inject(SessionService);
  return sessionService.isAuthenticated();
};
