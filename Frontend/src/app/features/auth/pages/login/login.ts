import { Component } from '@angular/core';
import { AuthLayoutComponent } from '../../../../shared/components/auth-layout/auth-layout';
import { LoginFormComponent } from '../../components/login-form/login-form';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [AuthLayoutComponent, LoginFormComponent],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginPage {}
