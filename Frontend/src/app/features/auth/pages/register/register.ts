import { Component } from '@angular/core';
import { AuthLayoutComponent } from '../../../../shared/components/auth-layout/auth-layout';
import { RegisterFormComponent } from '../../components/register-form/register-form';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [AuthLayoutComponent, RegisterFormComponent],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class RegisterPage {}
