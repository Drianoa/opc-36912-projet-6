import { HttpErrorResponse, type HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

export const notFoundInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router)
  return next(req).pipe(
    tap({
      error: (error: HttpErrorResponse) => {
        if (error.status === 404) {
          router.navigate(['/not-found']);
        }
      }
    })
  );
};
