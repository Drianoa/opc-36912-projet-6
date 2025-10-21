import { tap } from 'rxjs';
import type { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { Router } from '@angular/router';
import { inject } from '@angular/core';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router)
  return next(req).pipe(tap({
    error: (error: HttpErrorResponse) => {
      if (error.status >= 500 || error.status === 0) {
        router.navigate(['/error']);
      }
  }}));
};
