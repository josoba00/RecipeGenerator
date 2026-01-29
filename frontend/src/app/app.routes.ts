import { Routes } from '@angular/router';
import {Dashboard} from './views/dashboard/dashboard';
import {Recipes} from './views/recipes/recipes';
import {RecipeDetail} from './views/recipe-detail/recipe-detail';
import {RecipeCreate} from './views/recipe-create/recipe-create';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full',
    title: 'Home page',
  },
  {
    path: 'dashboard',
    component: Dashboard,
  },
  {
    path: 'recipes',
    component: Recipes,
  },
  {
    path: 'create',
    component: RecipeCreate,
  },
  {
    path: 'recipe-detail/:id',
    component: RecipeDetail,
    title: 'Recipe details',
  },
  {
    path: '**',
    redirectTo: '/dashboard',
  },
];
