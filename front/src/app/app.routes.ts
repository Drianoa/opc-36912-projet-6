import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {TopicComponent} from "./topic/topic.component";
import {TopicsComponent} from "./pages/topics/topics.component";
import {NavigationComponent} from "./core/composants/navigation/navigation.component";
import {authGuard} from "./core/auth.guard";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
export const routes: Routes = [
  {
    path: '',
    component: NavigationComponent,
    pathMatch: 'full',
    canActivate: [authGuard],
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
  },
  // {
  //   path: 'login',
  // },
  // {
  //   path: 'register',
  // }

  ];
