import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { InputFieldComponent } from '../../../../shared/components/input-field/input-field';
import { CheckboxFieldComponent } from '../../../../shared/components/checkbox-field/checkbox-field';
import { RadioCardComponent } from '../../../../shared/components/radio-card/radio-card';
import { AuthButtonComponent } from '../../../../shared/components/auth-button/auth-button';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    InputFieldComponent,
    CheckboxFieldComponent,
    RadioCardComponent,
    AuthButtonComponent
  ],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss'
})
export class LoginFormComponent {
  private readonly fb = inject(FormBuilder);

  protected readonly loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    firstLogin: [false],
    rememberMe: [false]
  });

  protected readonly emailErrors: Record<string, string> = {
    required: 'El correo electrónico es obligatorio.',
    email: 'Ingresa un correo electrónico válido.'
  };

  protected readonly passwordErrors: Record<string, string> = {
    required: 'La contraseña es obligatoria.',
    minlength: 'La contraseña debe tener al menos 6 caracteres.'
  };

  protected onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    // TODO: connect with backend when required
    console.log('Login payload:', this.loginForm.value);
  }
}
