import {Routes} from '@angular/router';
import {HomeComponent} from './pages/unauth/home/home.component';
import {TopicsComponent} from "./pages/auth/topics/topics.component";
import {NavigationComponent} from "./components/navigation/navigation.component";
import {RegisterComponent} from "./pages/unauth/register/register.component";
import {LoginComponent} from "./pages/unauth/login/login.component";
import {PostsComponent} from "./pages/auth/posts/posts.component";
import {MeComponent} from "./pages/auth/me/me.component";
import {NewPostComponent} from "./pages/auth/new-post/new-post.component";
import {PostComponent} from "./pages/auth/post/post.component";
import {AuthComponent} from "./components/auth/auth.component";
import { hasTopicsGuard } from './core/guards/hasTopics.guard';
import { authGuard } from './core/guards/auth.guard';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ErrorComponent } from './pages/error/error.component';

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
        canMatch: [hasTopicsGuard],
        path: '',
        redirectTo: 'posts',
        pathMatch: 'full'
      },
      {
        path: '',
        redirectTo: 'topics',
        pathMatch: 'full'
      },
      {
        path: 'topics',
        component: TopicsComponent,
        pathMatch: 'full'
      },
      {
        path: 'posts',
        component: PostsComponent,
        pathMatch: 'full'
      },
      {
        path: 'me',
        component: MeComponent,
        pathMatch: 'full'
      },
      {
        path: 'new-post',
        component: NewPostComponent,
        pathMatch: 'full'
      },
      {
        path: 'post/:id',
        component: PostComponent,
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  }, {
    path: 'auth',
    component: AuthComponent,
    children: [
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
    ]
  },{
    path: 'error',
    component: ErrorComponent,
    pathMatch: 'full'
  },{
    path: '**',
    component: NotFoundComponent
  }
];
