import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/auth/pages/login/login').then(m => m.LoginPage)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/pages/register/register').then(m => m.RegisterPage)
  },
  {
    path: 'auth/verify-code',
    loadComponent: () => import('./features/auth/pages/verify-code/verify-code').then(m => m.VerifyCodePage)
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./features/dashboard/layouts/dashboard-layout/dashboard-layout').then(m => m.DashboardLayoutComponent),
    children: [
      { path: '', loadComponent: () => import('./features/dashboard/pages/home/home').then(m => m.HomePage) }
    ]
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }
];
