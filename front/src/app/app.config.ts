import { ApplicationConfig, LOCALE_ID, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { provideAnimations } from '@angular/platform-browser/animations';
import { authInterceptor } from './core/interceptors/auth.interceptor';
import {routes} from "./app.routes";
import { notFoundInterceptor } from './core/interceptors/not-found.interceptor';
import { errorInterceptor } from './core/interceptors/error.interceptor';

registerLocaleData(localeFr);

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(
      routes
    ),
    provideHttpClient(
      withInterceptors([
        authInterceptor,
        notFoundInterceptor,
        errorInterceptor
      ])
    ),
    provideAnimations(),
    {
      provide: LOCALE_ID,
      useValue: 'fr-FR',
    }
  ]
};
