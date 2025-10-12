import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {TopicComponent} from "./topic/topic.component";
import {TopicsComponent} from "./pages/topics/topics.component";
import {NavigationComponent} from "./core/composants/navigation/navigation.component";
import {authGuard} from "./core/auth.guard";
import {RegisterComponent} from "./pages/register/register.component";
import {LoginComponent} from "./pages/login/login.component";

// Routes configuration:
// - Unauthenticated users see HomeComponent at root path
// - Authenticated users see NavigationComponent at root path with child routes
export const routes: Routes = [
  {
    path: '',
    component: NavigationComponent,
    canMatch: [authGuard],
    children: [
      {
        path: 'topics',
        component: TopicsComponent,
      }
    ]
  },
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },


  ];
