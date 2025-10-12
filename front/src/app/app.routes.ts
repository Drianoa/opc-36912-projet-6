import { Routes } from '@angular/router';
import { HomeComponent } from './pages/unauth/home/home.component';
import {TopicComponent} from "./components/topic/topic.component";
import {TopicsComponent} from "./pages/auth/topics/topics.component";
import {NavigationComponent} from "./core/composants/navigation/navigation.component";
import {authGuard} from "./core/auth.guard";
import {RegisterComponent} from "./pages/unauth/register/register.component";
import {LoginComponent} from "./pages/unauth/login/login.component";
import {PostsComponent} from "./pages/auth/posts/posts.component";

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
      },
      {
        path: 'posts',
        component: PostsComponent
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
