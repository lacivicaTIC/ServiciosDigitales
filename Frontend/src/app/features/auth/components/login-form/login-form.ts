import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators,
  FormControl
} from '@angular/forms';
import { Router } from '@angular/router';

import { InputFieldComponent } from '../../../../shared/components/input-field/input-field';
import { CheckboxFieldComponent } from '../../../../shared/components/checkbox-field/checkbox-field';
import { RadioCardComponent } from '../../../../shared/components/radio-card/radio-card';
import { AuthButtonComponent } from '../../../../shared/components/auth-button/auth-button';

import { AuthController } from '../../../../controllers/auth-controller';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputFieldComponent,
    CheckboxFieldComponent,
    RadioCardComponent,
    AuthButtonComponent
  ],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss'
})
export class LoginFormComponent implements OnInit {

  hide = true;

  loading = false;
  submitted = false;

  authForm!: UntypedFormGroup;

  protected readonly showForgotPasswordHint = signal(false);

  constructor(
    public formBuilder: UntypedFormBuilder,
    private _controllerAuth: AuthController,
    public router: Router
  ) {}

  ngOnInit(): void {
    this._controllerAuth.init(this);
  }

  /**
   * Envía el formulario al AuthController
   */
  protected onSubmit(): void {
    this._controllerAuth.onSubmit(this);
  }

  /**
   * Mostrar ayuda para recuperar contraseña
   */
  protected onForgotPassword(): void {
    this.showForgotPasswordHint.set(true);

    const emailControl = this.authForm.get('email');

    if (emailControl) {
      emailControl.markAsTouched();
      emailControl.markAsDirty();
    }
  }

  /**
   * Controles del formulario
   *
   * Se convierten explícitamente a FormControl para
   * que los componentes InputField y CheckboxField
   * no reciban AbstractControl.
   */
  get emailControl(): FormControl {
    return this.authForm.get('email') as FormControl;
  }

  get passwordControl(): FormControl {
    return this.authForm.get('password') as FormControl;
  }

  get rememberMeControl(): FormControl {
    return this.authForm.get('rememberMe') as FormControl;
  }

  /**
   * Acceso corto a los controles para AuthController
   */
  get f() {
    return this.authForm.controls;
  }

  /**
   * Mensajes de error del correo
   */
  protected readonly emailErrors: Record<string, string> = {
    required: 'El correo electrónico es obligatorio.',
    email: 'Ingresa un correo electrónico válido.'
  };

  /**
   * Mensajes de error de contraseña
   */
  protected readonly passwordErrors: Record<string, string> = {
    required: 'La contraseña es obligatoria.',
    minlength: 'La contraseña debe tener al menos 6 caracteres.'
  };
}